package api

import api.models.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * This repository provides access to raw https://bund.dev
 * data.
 *
 * Call `fetchAutobahns` to get a full loaded dataset.
 */
class AutobahnApiRepository {

    // MARK: - Internal helper -

    /**
     * Will fetch a set of new Autobahn instances from API.
     *
     * @param save: If true, the method will save the resulting
     *              JSON to disk.
     * @param completion: Completion block that will contain all
     *              fetched objects.
     */
    suspend fun fetchAutobahns(save: Boolean = true, completion: ((List<Autobahn>) -> Unit)? = null) {
        // Log start time of fetching flow.
        val startTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)

        // 1. Get Autobahn ids
        // from: https://verkehr.autobahn.de/o/autobahn/
        logger.debug("Get all Autobahn IDs")

        val ids: AutobahnIds = client.get(AUTOBAHN_URL)

        // 2. Iterate over road ids
        val autobahnen: List<Autobahn> = ids.roads
            .filter { !it.contains("/") }
            .map {

                // Fetch information.
                logger.debug("Fetching roadworks for ID $it")
                val roadworks: Roadworks = client.get("$AUTOBAHN_URL/$it/services/roadworks")
                val webcams: Webcams = client.get("$AUTOBAHN_URL/$it/services/webcam")
                val warnings: Warnings = client.get("$AUTOBAHN_URL/$it/services/warning")
                val closures: Closures = client.get("$AUTOBAHN_URL/$it/services/closure")
                val chargingStations: ElectricChargingStations =
                    client.get("$AUTOBAHN_URL/$it/services/electric_charging_station")

                // Create Autobahn object.
                logger.debug("Building data class for ID $it")
                Autobahn(
                    it,
                    roadworks.roadworks,
                    webcams.webcam, warnings.warning,
                    closures.closure,
                    chargingStations.electric_charging_station
                )
            }

        // Close http connection
        client.close()

        // Create container object with current time stamp.
        val autobahns = Autobahns(
            LocalDateTime.now().toString(),
            autobahnen
        )

        // 3. Check if result should be saved to disk.
        if (save) {
            // Create json file
            val json = Json.encodeToString(autobahns)

            // Store json.
            save(json, "autobahns.json", true)
        }

        // 4. Log duration
        val deltaSeconds = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - startTime
        logger.debug("It took $deltaSeconds seconds to finish.")

        // 5. Call completion if set
        completion?.invoke(autobahnen)
    }

    // MARK: - Private helper -

    /**
     * Tries to write given input to file with given file name.
     *
     * @param input: Input string.
     * @param filename: Filename of the created file.
     * @param overrideExistingFile: If true, existing files will be overridden.
     */
    private fun save(input: String, filename: String, overrideExistingFile: Boolean = true) {
        val file = File(filename)
        val fileExists = file.exists()

        // If file already exists and ´overrideExistingFile` is true
        // -> delete old file.
        if (fileExists && overrideExistingFile) {
            logger.debug("Deleting already existing file at path: $filename")
            file.delete()
        }

        // If file already exists but ´overrideExistingFile` is false
        // -> abort operation
        if (fileExists && !overrideExistingFile) {
            logger.debug("File cannot be written, because it already exists at path: $filename")
            return
        }

        // Write file to disc.
        file.writeText(input)
        logger.debug("Wrote file to path ${file.path}")
    }

    // MARK: - Companion object -

    companion object {
        /**
         * Base url of federal Autobahn API.
         */
        private const val BASE_URL = "https://verkehr.autobahn.de/o"

        /**
         * Endpoint url of autobahn ids.
         */
        private const val AUTOBAHN_URL = "${BASE_URL}/autobahn/"

        /**
         * Repository's logger.
         */
        private val logger = LoggerFactory.getLogger(AutobahnApiRepository::class.java)

        /**
         * Http client which will be used for all network requests.
         */
        private val client = HttpClient(CIO) {

            // Setup logging.
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.NONE
            }

            // Setup json serializer.
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
    }
}
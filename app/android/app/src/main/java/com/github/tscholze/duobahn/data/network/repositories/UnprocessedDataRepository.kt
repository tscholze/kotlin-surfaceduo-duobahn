package com.github.tscholze.duobahn.data.network.repositories

import com.github.tscholze.duobahn.data.domain.models.toModel
import com.github.tscholze.duobahn.data.network.dto.Autobahn
import com.github.tscholze.duobahn.data.network.dto.Autobahns
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.slf4j.LoggerFactory

class UnprocessedDataRepository: KoinComponent {

    // MARK: - Private properties -

    private var autobahns = listOf<com.github.tscholze.duobahn.data.domain.models.Autobahn>()

    // MARK: - Internal getters -

    fun getAutobahns(): List<com.github.tscholze.duobahn.data.domain.models.Autobahn>
    {
        return autobahns

        /*
        // If cached data is empty, fetch data from remote.
        // Else use cached data.
        if (autobahns.count() == 0)
        {
            coroutineScope {
                val operation = async { fetchAutobahns() }
                operation.await()
            }
        }
        else
        {
            return autobahns
        }
         */
    }

    suspend fun fetchAutobahns(
        completion: ((List<com.github.tscholze.duobahn.data.domain.models.Autobahn>) -> Unit)? = null
    ) {
        // 1. Fetch data from remote.
        val container = client.get<Autobahns>(urlString = URL)

        // 2. Cache fetched data.
        autobahns = container.autobahns.map { it.toModel() }

        // 3. Invoke optional completion block.
        completion?.invoke(autobahns)
    }

    // MARK: - Companion -

    companion object {
        /**
         * Endpoint url of autobahn ids.
         */
        private const val URL = "https://raw.githubusercontent.com/tscholze/kotlin-aggregator-script-duobahn/main/autobahns.json"

        /**
         * Repository's logger.
         */
        private val logger = LoggerFactory.getLogger(UnprocessedDataRepository::class.java)

        /**
         * Http client which will be used for all network requests.
         */
        private val client = HttpClient(CIO) {

            // Setup json serializer.
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        useAlternativeNames
                    }
                )

                acceptContentTypes = acceptContentTypes + ContentType.Any
            }
        }
    }
}
package api.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class AutobahnIds(
    /**
     * List of road identifiers.
     * E.g. "A1"
     */
    val roads: List<String>
)

/**
 * Describes an Autobahn with all its
 * available information.
 */
@Serializable
data class Autobahn(
    /**
     * ID / Name of the Autobahn
     */
    val id: String,

    /**
     * List of roadworks.
     */
    val roadworks: List<Roadwork>,

    /**
     * List of webcams.
     */
    val webcams: List<Webcam>,

    /**
     * List of road warnings.
     */
    val warnings: List<Warning>,

    /**
     * List of road closures (blocks)
     */
    val closures: List<Closure>,

    /**
     * List of e-charging stations.
     */
    val electricChargingStations: List<ElectricChargingStation>
)

@Serializable
data class Autobahns(
    val updatedAt: String,
    val autobahns: List<Autobahn>
)
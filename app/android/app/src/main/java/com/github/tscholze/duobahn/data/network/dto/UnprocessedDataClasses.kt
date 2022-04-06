package com.github.tscholze.duobahn.data.network.dto
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonNames

// MARK: - Helpers -

@Serializable
data class Coordinate(
    val lat: String,
    val long: String
)

// MARK: - Autobahns -

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

// MARK: - Closures -

@Serializable
data class Closure(
    val coordinate: Coordinate,
    val description: List<String>,
    val display_type: String,
    val extent: String,
    val footer: List<String>,
    val future: Boolean,
    val icon: String,
    val identifier: String,
    // val impact: Impact?,
    val isBlocked: String,
    val point: String,
    val startTimestamp: String,
    val subtitle: String,
    val title: String
)

@Serializable
data class Impact(
    val lower: String,
    val symbols: List<String>,
    val upper: String
)

// MARK: - E-charger -

@Serializable
data class ElectricChargingStation(
    val coordinate: Coordinate,
    val description: List<String>,
    val display_type: String,
    val extent: String,
    val footer: List<String>,
    val future: Boolean,
    val icon: String,
    val identifier: String,
    val isBlocked: String,
    val point: String,
    val subtitle: String,
    val title: String
)

// MARK: - Roadworks -

@Serializable
data class Roadwork(
    val coordinate: Coordinate,
    val description: List<String>,
    val display_type: String,
    val extent: String,
    val future: Boolean,
    val icon: String,
    val identifier: String,
    val isBlocked: String,
    val point: String,
    val startTimestamp: String,
    val subtitle: String,
    val title: String
)

// MARK: - Warnings -

@Serializable
data class Warning(
    val coordinate: Coordinate,
    val description: List<String>,
    val display_type: String,
    val extent: String,
    val footer: List<String>,
    val future: Boolean,
    val icon: String,
    val identifier: String,
    val isBlocked: String,
    val point: String,
    val startTimestamp: String,
    val subtitle: String,
    val title: String
)

// MARK - Webcams -

@Serializable
data class Webcam(
    val coordinate: Coordinate,
    val description: List<String>,
    val display_type: String,
    val extent: String,
    val footer: List<String>,
    val future: Boolean,
    val icon: String,
    val identifier: String,
    @JsonNames("imageurl")
    val imageUrl: String,
    val isBlocked: String,
    @JsonNames("linkurl")
    val linkUrl: String,
    val `operator`: String,
    val point: String,
    val subtitle: String,
    val title: String
)


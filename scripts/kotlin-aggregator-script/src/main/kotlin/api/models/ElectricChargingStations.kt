package api.models

import kotlinx.serialization.Serializable

@Serializable
data class ElectricChargingStations(
    val electric_charging_station: List<ElectricChargingStation>
)

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
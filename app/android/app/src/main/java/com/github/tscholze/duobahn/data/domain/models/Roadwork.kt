package com.github.tscholze.duobahn.data.domain.models

import com.microsoft.maps.Geopoint
import java.time.LocalDateTime


// MARK: - Data -

/**
 * Represents a roadwork.
 *
 * @property id Unique identifier.
 * @property title Human-readable name of the roadwork.
 * @property start Start date of the roadwork.
 * @property end Optional end date of the roadwork.
 * @property reason Optional reason why the roadwork exists.
 * @property restriction Optional restrictions on how the traffic flows.
 * @property widthLimitedTo Optional width of lane that's still open.
 */
data class Roadwork(
    override val id: String,
    override val title: String,
    override val location: Geopoint,
    override val imageId: Int = com.github.tscholze.duobahn.R.drawable.ic_map_roadwork,

    val start: LocalDateTime,
    val end: LocalDateTime?,
    val reason: String?,
    val restriction: String?,
    val widthLimitedTo: String?,
) : Mapable

// MARK: - From Mapper -

/**
 * Maps the DTO into the domain model.
 */
fun com.github.tscholze.duobahn.data.network.dto.Roadwork.toModel(): Roadwork  {

    val startString = description
        .first { it.startsWith("Beginn:")}
        .replace("Beginn:", "")
        .trim()

    val endString = description
        .firstOrNull { it.startsWith("Ende:")}
        ?.replace("Ende:", "")
        ?.trim()

    val reasonString = description
        .firstOrNull { it.startsWith("Art der Maßnahme:")}
        ?.replace("Art der Maßnahme:", "")
        ?.trim()

    val restrictionsString = description
        .firstOrNull { it.startsWith("Einschränkungen:")}
        ?.replace("Einschränkungen:", "")
        ?.split("\n")
        ?.map { it.isNotBlank() }
        ?.joinToString { "" }
        ?.trim()

    val widthLimitedToString = description
        .firstOrNull { it.startsWith("Maximale Durchfahrsbreite:")}
        ?.replace("Maximale Durchfahrsbreite:", "")
        ?.split("\n")
        ?.map { it.isNotBlank() }
        ?.joinToString { "" }
        ?.trim()

    val location = this.coordinate.toModel().toGeoPoint()
    val startDate = startString.toModelDate()
    val endDate = endString?.toModelDate()

    return Roadwork(
        id = identifier,
        title = title,
        start = startDate,
        end =  endDate,
        reason = reasonString,
        restriction = restrictionsString,
        widthLimitedTo = widthLimitedToString,
        location = location
    )
}
package com.github.tscholze.duobahn.data.domain.models

import com.github.tscholze.duobahn.data.domain.models.MarkerDefinition.MarkerType.ROADWORK
import java.time.LocalDateTime

// MARK: - Data -

/**
 * Represents a roadwork.
 *
 * @property id Unique identifier.
 * @property name Human-readable name of the roadwork.
 * @property start Start date of the roadwork.
 * @property end Optional end date of the roadwork.
 * @property reason Optional reason why the roadwork exists.
 * @property restriction Optional restrictions on how the traffic flows.
 * @property widthLimitedTo Optional width of lane that's still open.
 */
data class Roadwork(
    val id: String,
    val name: String,
    val start: LocalDateTime,
    val end: LocalDateTime?,
    val reason: String?,
    val restriction: String?,
    val widthLimitedTo: String?,
    val coordinate: Coordinate
)

// MARK: - To Mapper -

/**
 * Maps the roadwork to a map marker.
 */
fun Roadwork.toMarkerDefinition() =
    MarkerDefinition(
        type = ROADWORK,
        title = name,
        coordinate = coordinate.toLngLat(),
        snippet = null,
    )

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

    val coordinate = this.coordinate.toModel()
    val startDate = startString.toModelDate()
    val endDate = endString?.toModelDate()

    return Roadwork(
        id = identifier,
        name = title,
        start = startDate,
        end =  endDate,
        reason = reasonString,
        restriction = restrictionsString,
        widthLimitedTo = widthLimitedToString,
        coordinate = coordinate
    )
}



package com.github.tscholze.duobahn.data.domain.models

// MARK: - Data -

/**
 * Represents an autobahn.
 *
 * @property id Unique identifier of an autobahn.
 * @property name Name e.g. "A1"
 * @property roadworks List of existing roadworks along the road
 */
data class Autobahn(
    val id: String,
    val name: String,
    val roadworks: List<Roadwork>,
)

// MARK: - From Mapper -

/**
 * Maps the DTO into the domain model.
 */
fun com.github.tscholze.duobahn.data.network.dto.Autobahn.toModel() = Autobahn(
    id = id,
    name = id,
    roadworks = roadworks.map { it.toModel() },
)
package com.github.tscholze.duobahn.data.domain.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Gets a date from a preformatted string.
 *
 * Required format: "dd.MM.yyyy HH:mm")
 */
fun String.toModelDate(): LocalDateTime
    = LocalDateTime.parse(this, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
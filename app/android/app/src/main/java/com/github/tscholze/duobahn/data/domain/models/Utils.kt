package com.github.tscholze.duobahn.data.domain.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.toModelDate()
        = LocalDateTime.parse(this, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
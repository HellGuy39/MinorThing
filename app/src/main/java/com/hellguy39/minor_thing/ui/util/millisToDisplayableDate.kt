package com.hellguy39.minor_thing.ui.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun millisToDisplayableDate(millis: Long): String {
    return Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(DateTimeFormatter.ofPattern( "dd MMM yyyy" ))
}
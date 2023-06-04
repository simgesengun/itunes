package com.simgesengun.itunes.util.gson

import java.util.TimeZone

object GsonConstants {
    const val GSON_DATEFORMAT = "yyyy-MM-dd'T'HH:mm:SS'Z'"
    val utcTimeZone: TimeZone = TimeZone.getTimeZone("UTC")
}

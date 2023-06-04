package com.simgesengun.itunes.util.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.simgesengun.itunes.util.gson.GsonConstants.GSON_DATEFORMAT
import com.simgesengun.itunes.util.gson.GsonConstants.utcTimeZone
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateDeserializer @Inject constructor() : JsonDeserializer<Date> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? = runCatching {
        SimpleDateFormat(GSON_DATEFORMAT, Locale.getDefault()).apply {
            timeZone = utcTimeZone
            isLenient = false
        }.parse(json.asString)
    }.getOrNull()
}
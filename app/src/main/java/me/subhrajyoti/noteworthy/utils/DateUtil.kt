package me.subhrajyoti.noteworthy.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtil {

    fun convertTimestampToReadableDateTime(timeStamp: Long, zoneId: ZoneId = ZoneId.systemDefault()): String {
        val df: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm").withZone(zoneId)
        return df.format(Instant.ofEpochMilli(timeStamp))
//        val date = Times(timeStamp)
//        val dateFormatter = SimpleDateFormat(, Locale.US)
//        return dateFormatter.format(date)
    }
}
package me.subhrajyoti.noteworthy.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun convertTimestampToReadableDateTime(timeStamp: Long): String {
        val date = Date(timeStamp)
        val dateFormatter = SimpleDateFormat("dd MMM yyyy HH:mm aa", Locale.US)
        return dateFormatter.format(date)
    }
}
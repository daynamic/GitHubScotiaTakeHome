package com.akshat.githubscotiatakehome.utils

import java.text.ParseException
import java.text.SimpleDateFormat


fun formatWords(string: String): String {
    return string.trim().split("\\s+".toRegex()).joinToString(" ") { it.capitalize() }
}




fun formatDate(dateString: String): String {
    val zonedFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val stringDateFormat = SimpleDateFormat("MMMM dd, uuuu")
    val yourDesiredFormat = SimpleDateFormat("dd-MMM-YYYY")
    var dateStringToReturn = ""
    try {
        val date  = zonedFormat.parse(dateString)
        dateStringToReturn = yourDesiredFormat.format(date)
    } catch (e: ParseException) {
        try {
            var date = stringDateFormat.parse(dateString)
            dateStringToReturn = yourDesiredFormat.format(date)
        } catch (e: ParseException) {
        }
    }

    return dateStringToReturn
}
package com.batdemir.core.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDate(format: DateFormat, locale: Locale = Locale.ROOT): Date? {
    val simpleDateFormat = SimpleDateFormat(format.toString(), locale)
    return simpleDateFormat.parse(this)
}

fun String.toDateFormat(
    inputFormat: DateFormat,
    outputFormat: DateFormat,
    locale: Locale = Locale.ROOT
): String? {
    val date = this.toDate(inputFormat)
    return date?.toString(outputFormat, locale)
}

fun Date.toString(format: DateFormat, locale: Locale = Locale.ROOT): String {
    val simpleDateFormat = SimpleDateFormat(format.toString(), locale)
    return simpleDateFormat.format(this)
}

enum class DateFormat(private val string: String) {
    CALENDER_DATE_FORMAT("EEE MMM dd hh:mm:ss 'GMT'Z yyyy"),
    DEFAULT_DATE_FORMAT("yyyy-MM-dd'T'HH:mm:ss"),
    NORMAL_DATE_FORMAT("yyyy-MM-dd HH:mm:ss"),
    SMALL_DATE_FORMAT("yyyy-MM-dd"),
    SMALL_TIME_FORMAT("HH:mm"),
    SHOW_DATE_FORMAT("dd.MM.yyyy"),
    SHOW_TIME_FORMAT("HH:mm:ss"),
    SHOW_FULL_FORMAT("dd.MM.yyyy HH:mm");

    override fun toString(): String {
        return string
    }
}

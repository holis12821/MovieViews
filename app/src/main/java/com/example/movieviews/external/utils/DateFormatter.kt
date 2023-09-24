package com.example.movieviews.external.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {

    private const val formattedInput = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private const val formattedOutput = "yyyy-MM-dd hh:mm:ss a"
    private const val strandartFullyFormat = "yyyy-MM-dd HH:mm:ss"
    private const val format_ddMMMyyyy = "dd MMM yyyy"

    fun getDateFormatting(timestamp: String): String? {
        val inputFormat = SimpleDateFormat(formattedInput, Locale.ENGLISH)
        val outputFormat = SimpleDateFormat(formattedOutput, Locale.ENGLISH)
        val parsedDate = inputFormat.parse(timestamp)
        val afterParsingDate = parsedDate?.let { outputFormat.format(it) }
        return afterParsingDate?.let { getParsingDate(it) }
    }

    private fun getParsingDate(input: String): String {
        val from = SimpleDateFormat(strandartFullyFormat, Locale.ENGLISH)
        val to = SimpleDateFormat(format_ddMMMyyyy, Locale.ENGLISH)
        val date: Date?
        return try {
            date = from.parse(input)
            to.format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
            input
        }
    }
}
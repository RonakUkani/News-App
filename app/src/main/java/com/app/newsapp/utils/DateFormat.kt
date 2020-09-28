package com.app.newsapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

const val serverDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val outPutDateFormat = "yyyy-MM-dd"

@SuppressLint("SimpleDateFormat")
fun convertServerDate(inputDate: String): String {
    return try {
        if (inputDate.isNotEmpty()) {
            val inputFormat = SimpleDateFormat(serverDateFormat)
            val outputFormat = SimpleDateFormat(outPutDateFormat)
            val date = inputFormat.parse(inputDate)
            return outputFormat.format(date!!)
        } else {
            ""
        }
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
package com.example.mytodolist.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateFormatUtil {
    companion object {
        fun getStandardDateFormat(date: Date): String{
            return SimpleDateFormat("MM/dd/yyyy 'at' hh:mm a", Locale.getDefault()).format(date)
        }
    }
}
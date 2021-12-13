package com.example.jpa.util

import java.sql.Timestamp

object TimestampUtil {

    fun getCurrentTimestamp(): Timestamp {
        return Timestamp(System.currentTimeMillis())
    }

}
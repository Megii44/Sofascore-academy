package com.example.minisofascore.data.models

data class Score(
    val total: Int,
    val period1: Int,
    val period2: Int,
    val period3: Int,
    val period4: Int,
    val overtime: Int
): java.io.Serializable
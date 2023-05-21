package com.example.minisofascore.data.models

import android.graphics.drawable.Drawable
import java.lang.ref.Reference
import java.util.Date

data class Event(
    val startTime: String,
    val overTime: String,
    val logoTeam1: Drawable,
    val logoTeam2: Drawable,
    val titleTeam1: String,
    val titleTeam2: String,
    val scoreTeam1: Int,
    val scoreTeam2: Int
)
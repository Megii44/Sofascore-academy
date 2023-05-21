package com.example.minisofascore.data.models

import android.graphics.drawable.Drawable

data class League (
    val name: String,
    val icon: Drawable,
    val contestants: List<String>
)

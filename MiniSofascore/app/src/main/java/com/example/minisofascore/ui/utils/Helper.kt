package com.example.minisofascore.ui.utils

import com.example.minisofascore.data.enums.SportEnum

fun getSportForPosition(position: Int): SportEnum {
    return when (position) {
        0 -> SportEnum.Football
        1 -> SportEnum.Basketball
        2 -> SportEnum.AmericanFootball
        else -> throw IllegalArgumentException("Invalid position")
    }
}

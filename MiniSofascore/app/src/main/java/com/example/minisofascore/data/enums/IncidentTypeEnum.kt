package com.example.minisofascore.data.enums

enum class IncidentTypeEnum {
    Goal {
        override fun toString(): String {
            return "goal"
        }
    },
    Card {
        override fun toString(): String {
            return "card"
        }
    },
    Period {
        override fun toString(): String {
            return "period"
        }
    }
}

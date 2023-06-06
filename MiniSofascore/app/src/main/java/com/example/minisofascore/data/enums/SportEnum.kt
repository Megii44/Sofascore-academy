package com.example.minisofascore.data.enums

enum class SportEnum {
    Football {
        override fun toString(): String {
            return "football"
        }
    },
    Basketball {
        override fun toString(): String {
            return "basketball"
        }
    },
    AmericanFootball {
        override fun toString(): String {
            return "american-football"
        }
    }
}

package com.example.minisofascore.data.enums

enum class EventStatusEnum {
    InProgress {
        override fun toString(): String {
            return "inprogress"
        }
    },
    Finished {
        override fun toString(): String {
            return "finished"
        }
    },
    NotStarted {
        override fun toString(): String {
            return "notstarted"
        }
    }
}

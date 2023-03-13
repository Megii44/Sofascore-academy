package com.example.homework_3.enum

enum class SizeEnum {
    XXS,
    XS,
    S,
    M,
    L,
    XL,
    XXL;

    companion object {
        fun getValues(): Array<String> {
            return values().map { it.name }.toTypedArray()
        }
    }
}
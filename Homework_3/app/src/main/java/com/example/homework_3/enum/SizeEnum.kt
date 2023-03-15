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

        public fun fromStringToSizeEnum(str: String): SizeEnum {
            return when (str) {
                "XXS" -> SizeEnum.XXS
                "XS" -> SizeEnum.XS
                "S" -> SizeEnum.S
                "M" -> SizeEnum.M
                "L" -> SizeEnum.L
                "XL" -> SizeEnum.XL
                "XXL" -> SizeEnum.XXL
                else -> SizeEnum.XXL
            }
        }
    }

}
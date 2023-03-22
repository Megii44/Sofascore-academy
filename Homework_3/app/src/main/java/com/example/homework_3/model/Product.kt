package com.example.homework_3.model

import com.example.homework_3.enum.SizeEnum

class Product(
    var name: String,
    var description: String,
    var brand: String,
    var category: String,
    var productType: String,
    var style: String,
    var color: String,
    var material: String,
    var size: SizeEnum,
    var price: String,
    var productImageUrl: String,
) :java.io.Serializable {
    override fun toString(): String {
        return "Name=$name\nDescription=$description\nBrand=$brand\n" +
                "Category=$category\nProduct type=$productType\nStyle=$style\n" +
                "Color=$color\nMaterial=$material\nSize=$size\nPrice=$price\n"
    }
}

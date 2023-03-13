package com.example.homework_3.model

class Product(
    var name: String,
    var description: String,
    var brand: String,
    var category: String,
    var productType: String,
    var style: String,
    var color: String,
    var material: String,
    var size: String,
    var price: String
) {
    override fun toString(): String {
        return "Name=$name\nDescription=$description\nBrand=$brand\n" +
                "Category=$category\nProduct type=$productType\nStyle=$style\n" +
                "Color=$color\nMaterial=$material\nSize=$size\nPrice=$price\n"
    }
}

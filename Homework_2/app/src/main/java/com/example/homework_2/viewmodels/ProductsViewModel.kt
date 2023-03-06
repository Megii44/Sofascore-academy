package com.example.homework_2.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homework_2.model.Product

class ProductsViewModel : ViewModel() {

    // Get a list of products
    fun getProducts(): ArrayList<Product> {
        val products = ArrayList<Product>()

        val dress = Product(
            "Pretty in pink dress",
            "Sparkly pink dress",
            "Oh Polly",
            "Dress",
            "Mini dress",
            "A cut",
            "pink",
            "cotton",
            "xs",
            "50 euro"
        )

        val boots = Product(
            "Cowboy boots",
            "Knee high black cowboy boots",
            "Zapatos",
            "Shoes",
            "Boots",
            "Cowboy",
            "black",
            "leather",
            "39",
            "90 euro"
        )
        products.add(dress)
        products.add(boots)

        return products
    }
}
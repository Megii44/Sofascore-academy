package com.example.homework_2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework_2.model.Product

class ProductsViewModel : ViewModel() {

    private val _products = MutableLiveData<ArrayList<Product>>()
    val products: LiveData<ArrayList<Product>> = _products

    fun addProduct(product:Product) {
        val productList = products.value ?: ArrayList()
        productList.add(product)
        _products.value = productList
    }
}
package com.example.homework_2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework_2.model.Product

class AddProductViewModel : ViewModel() {
    val productName = MutableLiveData<String>()
    val productDescription = MutableLiveData<String>()
    val productPrice = MutableLiveData<String>()
    val productCategory = MutableLiveData<String>()
    val productManufacturer = MutableLiveData<String>()
    val productModel = MutableLiveData<String>()
    val productColor = MutableLiveData<String>()
    val productWeight = MutableLiveData<String>()
    val productDimensions = MutableLiveData<String>()

    fun addProduct(product: Product) {
        // Do something when the submit button is clicked
    }
}
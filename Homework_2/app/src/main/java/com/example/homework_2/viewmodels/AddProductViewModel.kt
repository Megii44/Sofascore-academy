package com.example.homework_2.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework_2.model.Product
import com.example.homework_2.model.ProductsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddProductViewModel : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(ProductsUIState())
    val uiState: StateFlow<ProductsUIState> = _uiState.asStateFlow()

    // Handle business logic
    fun addProduct(product: Product) {

        _uiState.update { currentState ->
            currentState.copy(
                products = currentState.products.plus(product)
            )
        }
    }
}
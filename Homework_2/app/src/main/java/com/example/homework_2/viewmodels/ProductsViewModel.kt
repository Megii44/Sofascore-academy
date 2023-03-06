package com.example.homework_2.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homework_2.model.Product
import com.example.homework_2.model.ProductsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductsViewModel : ViewModel() {
    // Expose screen UI state
    private val _uiState = MutableStateFlow(ProductsUIState())
    val uiState: StateFlow<ProductsUIState> = _uiState.asStateFlow()

    // Display products in dynamic way
    fun displayProducts() {

    }
}
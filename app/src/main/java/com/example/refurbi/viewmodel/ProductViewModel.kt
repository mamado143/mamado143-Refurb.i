package com.example.refurbi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.refurbi.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel : ViewModel() {

    // Dummy product list for demonstration.
    private val _productList = MutableStateFlow(
        listOf(
            Product(1, "iPhone X", "High-quality refurbished iPhone X", 500.0),
            Product(2, "iPhone 8", "Reliable refurbished iPhone 8", 400.0)
        )
    )
    val productList: StateFlow<List<Product>> = _productList

    fun getProductById(id: Int): Product? {
        return _productList.value.find { it.id == id }
    }

    fun addProduct(product: Product) {
        val currentList = _productList.value.toMutableList()
        // Generate a new id by taking the maximum current id + 1.
        val newId = (currentList.maxOfOrNull { it.id } ?: 0) + 1
        currentList.add(product.copy(id = newId))
        _productList.value = currentList
    }

    fun updateProduct(updatedProduct: Product) {
        val currentList = _productList.value.toMutableList()
        val index = currentList.indexOfFirst { it.id == updatedProduct.id }
        if (index != -1) {
            currentList[index] = updatedProduct
            _productList.value = currentList
        }
    }
}

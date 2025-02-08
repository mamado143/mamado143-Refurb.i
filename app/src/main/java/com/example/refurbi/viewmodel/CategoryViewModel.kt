package com.example.refurbi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.refurbi.data.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoryViewModel : ViewModel() {
    private val _categories = MutableStateFlow(
        listOf(
            Category(1, "iPhones", "https://example.com/iphones.png"),
            Category(2, "iPads", "https://example.com/ipads.png"),
            Category(3, "MacBooks", "https://example.com/macbooks.png"),
            Category(4, "Accessories", "https://example.com/accessories.png")
        )
    )
    val categories: StateFlow<List<Category>> = _categories
}

package com.example.sr_blooms.state

import com.example.sr_blooms.model.CategoryModel

sealed class CategoryUiState {
    object Loading : CategoryUiState()
    data class Success(val categories: List<CategoryModel>, val isOffline: Boolean) : CategoryUiState()
    data class Error(val message: String, val isOffline: Boolean) : CategoryUiState()
}

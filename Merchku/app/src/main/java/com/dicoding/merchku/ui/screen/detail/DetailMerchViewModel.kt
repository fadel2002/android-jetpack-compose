package com.dicoding.merchku.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.merchku.data.MerchRepository
import com.dicoding.merchku.model.OrderMerch
import com.dicoding.merchku.model.Merch
import com.dicoding.merchku.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMerchViewModel(
    private val repository: MerchRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderMerch>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderMerch>>
        get() = _uiState

    fun getMerchById(merchId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderMerchById(merchId))
        }
    }

    fun addToCart(merch: Merch, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMerch(merch.id, count)
        }
    }
}
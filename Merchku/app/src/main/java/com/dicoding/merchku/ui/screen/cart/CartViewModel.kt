package com.dicoding.merchku.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.merchku.data.MerchRepository
import com.dicoding.merchku.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: MerchRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderMerch() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderMerch()
                .collect { orderReward ->
                    val totalRequiredPoint =
                        orderReward.sumOf { it.merch.requiredPoint * it.count }
                    _uiState.value = UiState.Success(CartState(orderReward, totalRequiredPoint))
                }
        }
    }

    fun updateOrderMerch(rewardId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMerch(rewardId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderMerch()
                    }
                }
        }
    }
}
package com.dicoding.merchku.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.merchku.data.MerchRepository
import com.dicoding.merchku.model.OrderMerch
import com.dicoding.merchku.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MerchRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderMerch>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderMerch>>>
        get() = _uiState

    fun getAllMerch() {
        viewModelScope.launch {
            repository.getAllMerch()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderMerches ->
                    _uiState.value = UiState.Success(orderMerches)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchMerch(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderMerches ->
                    _uiState.value = UiState.Success(orderMerches)
                }
        }
    }
}
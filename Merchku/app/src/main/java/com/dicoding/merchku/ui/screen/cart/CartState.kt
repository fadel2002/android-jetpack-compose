package com.dicoding.merchku.ui.screen.cart

import com.dicoding.merchku.model.OrderMerch

data class CartState(
    val orderMerch: List<OrderMerch>,
    val totalRequiredPoint: Int
)
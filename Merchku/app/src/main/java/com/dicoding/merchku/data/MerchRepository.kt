package com.dicoding.merchku.data

import com.dicoding.merchku.model.FakeMerchDataSource
import com.dicoding.merchku.model.OrderMerch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MerchRepository {

    private val orderMerches = mutableListOf<OrderMerch>()

    init {
        if (orderMerches.isEmpty()) {
            FakeMerchDataSource.dummyMerches.forEach {
                orderMerches.add(OrderMerch(it, 0))
            }
        }
    }

    fun getAllMerch(): Flow<List<OrderMerch>> {
        return flowOf(orderMerches)
    }

    fun getOrderMerchById(merchId: Long): OrderMerch {
        return orderMerches.first {
            it.merch.id == merchId
        }
    }

    fun updateOrderMerch(merchId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderMerches.indexOfFirst { it.merch.id == merchId }
        val result = if (index >= 0) {
            val orderMerch = orderMerches[index]
            orderMerches[index] =
                orderMerch.copy(merch = orderMerch.merch, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderMerch(): Flow<List<OrderMerch>> {
        return getAllMerch()
            .map { orderMerches ->
                orderMerches.filter { orderMerch ->
                    orderMerch.count != 0
                }
            }
    }

    fun searchMerch(query: String): Flow<List<OrderMerch>>{
        return getAllMerch()
            .map { orderMerches ->
                orderMerches.filter { orderMerch ->
                    orderMerch.merch.title.contains(query, ignoreCase = true)
                }
            }
    }

    companion object {
        @Volatile
        private var instance: MerchRepository? = null

        fun getInstance(): MerchRepository =
            instance ?: synchronized(this) {
                MerchRepository().apply {
                    instance = this
                }
            }
    }
}
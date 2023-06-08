package com.dicoding.merchku.di

import com.dicoding.merchku.data.MerchRepository


object Injection {
    fun provideRepository(): MerchRepository {
        return MerchRepository.getInstance()
    }
}
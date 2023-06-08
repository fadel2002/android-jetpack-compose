package com.dicoding.merchku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.merchku.data.MerchRepository
import com.dicoding.merchku.ui.screen.cart.CartViewModel
import com.dicoding.merchku.ui.screen.detail.DetailMerchViewModel
import com.dicoding.merchku.ui.screen.home.HomeViewModel
import com.dicoding.merchku.ui.screen.profile.ProfileViewModel

class ViewModelFactory(private val repository: MerchRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailMerchViewModel::class.java)) {
            return DetailMerchViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
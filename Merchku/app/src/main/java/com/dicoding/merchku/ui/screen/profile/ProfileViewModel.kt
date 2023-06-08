package com.dicoding.merchku.ui.screen.profile

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.dicoding.merchku.R

class ProfileViewModel : ViewModel() {
    private val _name = mutableStateOf("Fadel Pramaputra Maulana")
    val name: State<String> get() = _name

    private val _bio = mutableStateOf("Android Developer")
    val bio: State<String> get() = _bio

    private val _email = mutableStateOf("fadelpm2002@gmail.com")
    val email: State<String> get() = _email

    private val _location = mutableStateOf("Balikpapan, Indonesia")
    val location: State<String> get() = _location

    private val _image = mutableStateOf(R.drawable.my_photo)
    val image: State<Int> get() = _image
}
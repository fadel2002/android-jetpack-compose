package com.dicoding.merchku.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailMerch : Screen("home/{merchId}") {
        fun createRoute(merchId: Long) = "home/$merchId"
    }
}
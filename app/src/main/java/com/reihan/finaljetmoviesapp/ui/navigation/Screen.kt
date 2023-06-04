package com.reihan.finaljetmoviesapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object About : Screen("about")
    object DetailMovie : Screen("home/{movieId}") {
        fun createRoute(movieId: Long) = "home/$movieId"
    }
}

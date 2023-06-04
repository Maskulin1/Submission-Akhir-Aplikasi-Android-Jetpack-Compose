package com.reihan.finaljetmoviesapp.ui.screen.cart

import com.reihan.finaljetmoviesapp.model.OrderMovie

data class CartState(
    val orderMovie: List<OrderMovie>,
    val totalRequiredMoney: Int
)
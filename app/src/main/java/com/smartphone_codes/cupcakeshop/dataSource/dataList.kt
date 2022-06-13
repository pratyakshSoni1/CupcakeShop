package com.smartphone_codes.cupcakeshop.dataSource

import com.smartphone_codes.cupcakeshop.R

//our dataset in List with help of data class

val dataset = mutableListOf<Flavours>(
    Flavours(R.drawable.cake_lemon, R.string.flav_lemon,29.00),
    Flavours(R.drawable.cake_gems, R.string.flav_gems,39.00),
    Flavours(R.drawable.cake_chocolate, R.string.flav_chocolate,49.00),
    Flavours(R.drawable.cake_cherry, R.string.flav_cherry,39.00),
    Flavours(R.drawable.cake_orange, R.string.flav_orange,29.00),
    Flavours(R.drawable.cake_strawberry, R.string.flav_strawberry,49.00),
    Flavours(R.drawable.cake_vanilla, R.string.flav_vanilla,39.00)
)
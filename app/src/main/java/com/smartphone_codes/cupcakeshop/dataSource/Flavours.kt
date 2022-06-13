package com.smartphone_codes.cupcakeshop.dataSource

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


//simple data class for our datasource initialization
data class Flavours(@DrawableRes val prev:Int, @StringRes val flav_name:Int, val flavCost:Double)

package com.example.shoppinglist.domain

data class ShopItem(
    val id: Int,
    var name: String,
    var count: Int,
    var active: Boolean
)

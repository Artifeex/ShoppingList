package com.example.shoppinglist.domain

data class ShopItem(
    var id: Int = UNDEFINED_ID,
    val name: String,
    val count: Int,
    val active: Boolean
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}

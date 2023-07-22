package com.example.shoppinglist.domain

interface ShopListRepository {

    fun addShopItem(newItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int):ShopItem

    fun getShopList(): List<ShopItem>
}
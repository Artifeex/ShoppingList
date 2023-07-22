package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(newItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int):ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}
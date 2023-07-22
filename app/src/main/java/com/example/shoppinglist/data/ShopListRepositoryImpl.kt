package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementedId = 0

    override fun addShopItem(newItem: ShopItem) {
        if(newItem.id == ShopItem.UNDEFINED_ID) {
            newItem.id = autoIncrementedId++
        }
        shopList.add(newItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id)
        deleteShopItem(oldShopItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Shop item with id $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList() //чтобы вернуть копию нашего списка
    }
}
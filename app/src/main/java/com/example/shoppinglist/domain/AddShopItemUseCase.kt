package com.example.shoppinglist.domain

class AddShopItemUseCase(
    private val shopListRepository: ShopListRepository
) {

    fun addShopItem(newItem: ShopItem) {
        shopListRepository.addShopItem(newItem)
    }
}
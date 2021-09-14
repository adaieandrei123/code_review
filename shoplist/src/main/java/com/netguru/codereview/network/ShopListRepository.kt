package com.netguru.codereview.network

class ShopListRepository(private val shopListApi: ShopListApi) {

    //ShopListApi interface for lists, listItems and events (updates)

    suspend fun getShopLists() = shopListApi.getShopLists()

    suspend fun getShopListItems(listId: String) = shopListApi.getShopListItems(listId)

    fun updateEvents() = shopListApi.getUpdateEvents()
}

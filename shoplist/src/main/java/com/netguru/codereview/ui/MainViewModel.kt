package com.netguru.codereview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netguru.codereview.network.ShopListApiMock
import com.netguru.codereview.network.ShopListRepository
import com.netguru.codereview.network.model.ShopListItemResponse
import com.netguru.codereview.network.model.ShopListResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    /*
    The ShopListRepository class implements an interface that has several
    methods defined, has as parameter a class that deals with
    the return of lists, items and updates (all dummy and with delay, update toast every 5 seconds)
     */

    private val shopListRepository = ShopListRepository(ShopListApiMock())



    val shopLists = MutableLiveData<List<Pair<ShopListResponse, List<ShopListItemResponse>>>>()
    private val eventLiveData = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            val lists = shopListRepository.getShopLists()
            val data = mutableListOf<Pair<ShopListResponse, List<ShopListItemResponse>>>()
            for (list in lists) {
                val items = shopListRepository.getShopListItems(list.list_id)
                data.add(list to items)
            }
            shopLists.postValue(data)
        }
        getUpdateEvents()
    }

    fun events(): LiveData<String> = eventLiveData
    //events is used in MainFragment to make that "Update $ it" index toast
    private fun getUpdateEvents() {
        GlobalScope.launch {
            shopListRepository.updateEvents().collect {
                eventLiveData.postValue(it)
            }
        }
    }
}

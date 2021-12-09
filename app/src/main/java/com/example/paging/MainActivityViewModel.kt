package com.example.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.paging.network.CharacterData
import com.example.paging.network.RetroInstance
import com.example.paging.network.RetroService


class MainActivityViewModel: ViewModel(){

    private var retroService: RetroService = RetroInstance.getRetroInstance()
                                                        .create(RetroService::class.java)

    fun getListData(): kotlinx.coroutines.flow.Flow<PagingData<CharacterData>>{

        return Pager (config = PagingConfig(pageSize = 42, maxSize = 200),
            pagingSourceFactory = {CharacterPagingSource(retroService)})
                                                        .flow.cachedIn(viewModelScope)
    }
}
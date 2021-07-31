package com.example.picsapp.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.picsapp.model.PixabayPicture
import com.example.picsapp.network.PixabayPageSource
import kotlinx.coroutines.flow.*

class PictureListViewModel: ViewModel() {

    fun getPictureList(categoryName: String): Flow<PagingData<PixabayPicture>>{
        return Pager(config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {PixabayPageSource(categoryName)}).flow.cachedIn(viewModelScope)
    }

}
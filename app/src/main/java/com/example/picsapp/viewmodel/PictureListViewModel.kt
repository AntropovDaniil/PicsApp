package com.example.picsapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import com.example.picsapp.model.PixabayPicture
import com.example.picsapp.network.PixabayApi
import com.example.picsapp.network.PixabayPageSource
import com.example.picsapp.network.RetrofitInstance
import com.example.picsapp.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PictureListViewModel(
): ViewModel() {

    fun getPictureList(categoryName: String): Flow<PagingData<PixabayPicture>>{
        return Pager(config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {PixabayPageSource(RetrofitInstance.pixabayApi, categoryName)}).flow.cachedIn(viewModelScope)
    }

}
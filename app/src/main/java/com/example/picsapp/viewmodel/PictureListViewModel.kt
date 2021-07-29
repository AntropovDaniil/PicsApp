package com.example.picsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsapp.model.PixabayPicture
import com.example.picsapp.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PictureListViewModel: ViewModel() {

    private val repository = CategoryRepository()

    val imageList: MutableLiveData<List<PixabayPicture>> by lazy {
        MutableLiveData<List<PixabayPicture>>()
    }

    private val pictureList = mutableListOf<PixabayPicture>()

    fun getPicturesFromCategory(categoryName: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPictureFromCategory(categoryName)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        response.body()?.let {
                            for (picture in it.hits)
                                pictureList.add(picture)
                        }
                        imageList.postValue(pictureList)
                    }
                }
                else{
                    //todo handle error
                    Log.d("TEST_TAG", "Error ${response.message()}")
                }
            }
        }
    }
}
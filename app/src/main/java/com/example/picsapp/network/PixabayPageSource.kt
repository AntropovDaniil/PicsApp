package com.example.picsapp.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.picsapp.model.PixabayPicture
import retrofit2.HttpException

class PixabayPageSource(
    private val pixabayService: PixabayApi,
    private val categoryName: String
): PagingSource<Int, PixabayPicture>() {

    override fun getRefreshKey(state: PagingState<Int, PixabayPicture>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixabayPicture> {
        if (categoryName.isEmpty()){
            LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize.coerceAtLeast(PixabayApi.MAX_PAGE_SIZE)

        val response = pixabayService.getPicturesFromCategory(categoryName, page =  page)
        val pictureList = mutableListOf<PixabayPicture>()

        if (response.isSuccessful) {
            response.body()?.let {
                for (picture in it.hits)
                    pictureList.add(picture)
            }
            val nextKey = if (pictureList.size < pageSize) null else page+1
            val prevKey = if (page == 1) null else page-1

            return LoadResult.Page(pictureList, prevKey = prevKey, nextKey = nextKey)
        }
        else {
            return LoadResult.Error(HttpException(response))
        }
    }
}
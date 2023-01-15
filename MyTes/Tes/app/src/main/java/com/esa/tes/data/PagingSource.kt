package com.esa.tes.data

import androidx.paging.PagingState
import com.esa.tes.data.network.api.ApiService
import com.esa.tes.data.network.response.DataUser
import androidx.paging.PagingSource

class PagingSource(
    private val apiService: ApiService
    ) : PagingSource<Int, DataUser>(){

    private companion object{
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataUser> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUsers(position)
            val listUsers = responseData.data

            LoadResult.Page(
                data = listUsers,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position -1,
                nextKey = if (listUsers.isEmpty()) null else position +1
            )
        }catch (exception: Exception){
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataUser>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
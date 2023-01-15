package com.esa.tes.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.esa.tes.data.PagingSource
import com.esa.tes.data.network.api.ApiService
import com.esa.tes.data.network.response.DataUser

class UserRepository(private val apiService: ApiService) {
    fun getUsers(): LiveData<PagingData<DataUser>> {
        return Pager (
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                PagingSource(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}
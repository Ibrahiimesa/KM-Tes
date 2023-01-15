package com.esa.tes.data.di

import android.content.Context
import com.esa.tes.data.network.api.ApiConfig
import com.esa.tes.data.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiClient()
        return UserRepository.getInstance(apiService)
    }
}
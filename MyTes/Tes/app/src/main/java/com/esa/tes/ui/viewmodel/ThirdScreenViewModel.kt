package com.esa.tes.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.esa.tes.data.network.response.DataUser
import com.esa.tes.data.repository.UserRepository

class ThirdScreenViewModel(private val repository: UserRepository): ViewModel() {
    fun getUsers(): LiveData<PagingData<DataUser>> {
       return repository.getUsers().cachedIn(viewModelScope)
    }
}


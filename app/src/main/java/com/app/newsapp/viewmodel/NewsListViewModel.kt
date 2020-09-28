package com.app.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.newsapp.api.CommonRepository
import com.app.newsapp.data.AppResponse
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val commonRepository: CommonRepository) :
    ViewModel() {
    val newsListSuccess = MutableLiveData<AppResponse>()
    val newsListFailure = MutableLiveData<String>()

    fun getNewsList() {
        commonRepository.getNewsList(viewModelScope,newsListSuccess,newsListFailure)
    }
}

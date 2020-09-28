package com.app.newsapp.api

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.app.newsapp.data.AppResponse
import com.app.newsapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CommonRepository @Inject constructor(
    context: Context, private val apiService: ApiService
) : BaseDataSource(context) {

    fun getNewsList(
        viewModelScope: CoroutineScope,
        newsListSuccess: MutableLiveData<AppResponse>,
        newsListFailure: MutableLiveData<String>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                apiService.getNewsList(Constants.KEY_COUNTRY,Constants.KEY_CATEGORY,Constants.KEY_API_KEY)
            }.fold(
                {
                    newsListSuccess.postValue(it)
                }, {
                    handleError(it) { error ->
                        newsListFailure.postValue(error)
                    }
                })
        }
    }

}
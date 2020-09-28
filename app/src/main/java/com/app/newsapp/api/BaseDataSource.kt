package com.app.newsapp.api

import android.content.Context
import com.app.newsapp.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BaseDataSource(private val context: Context) {


    fun handleError(it: Throwable, callback: (String) -> Unit = {}) {
        when (it) {
            is ConnectException -> {
                callback.invoke(context.getString(R.string.error_message_unable_to_connect))
            }
            is SocketTimeoutException -> {
                callback.invoke(context.getString(R.string.error_message_unable_to_connect))
            }
            is HttpException -> {
                callback.invoke(context.getString(R.string.error_message_unable_to_connect))
            }
            else -> {
                callback.invoke(context.getString(R.string.error_message_something_went_wrong))
            }
        }
    }


}



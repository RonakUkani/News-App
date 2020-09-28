package com.app.newsapp.di

import android.content.Context
import com.app.newsapp.BuildConfig
import com.app.newsapp.R
import com.app.newsapp.api.ApiService
import com.app.newsapp.api.CommonRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideCommonRepository(
        context: Context,
        apiService: ApiService
    ): CommonRepository {
        return CommonRepository(context, apiService)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        context: Context,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        @Named("log") logInterceptor: Interceptor
    ): Retrofit {
        return Retrofit.Builder()
            .client(
                okHttpClient.newBuilder()
                    .addInterceptor(logInterceptor)
                    .build()
            )
            .baseUrl(context.getString(R.string.apiUrl))
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    @Named("log")
    fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE
        }
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setVersion(1.0).create()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideSimpleOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()
    }

}
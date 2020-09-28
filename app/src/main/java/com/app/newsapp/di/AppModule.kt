package com.app.newsapp.di

import android.content.Context
import com.app.newsapp.NewsApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: NewsApplication): Context {
        return application.applicationContext
    }

}
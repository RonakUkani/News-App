package com.app.newsapp.di

import androidx.lifecycle.ViewModelProvider
import com.app.newsapp.di.AppViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
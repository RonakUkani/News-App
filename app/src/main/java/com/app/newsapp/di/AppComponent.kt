package com.app.newsapp.di

import com.app.newsapp.NewsApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class, ActivityBindingModule::class,
        ViewModelModule::class, ApiModule::class]
)

interface AppComponent : AndroidInjector<NewsApplication> {
    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<NewsApplication>
}



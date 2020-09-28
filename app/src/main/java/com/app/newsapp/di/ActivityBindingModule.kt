package com.app.newsapp.di

import com.app.newsapp.ui.activity.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [NewsListModule::class])
    internal abstract fun newsListActivity(): NewsActivity

}
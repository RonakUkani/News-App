package com.app.newsapp.di

import androidx.lifecycle.ViewModel
import com.app.newsapp.ui.fragment.NewsListFragment
import com.app.newsapp.viewmodel.NewsListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
internal abstract class NewsListModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    internal abstract fun bindViewModel(viewModel: NewsListViewModel): ViewModel

    /**
     * Generates an [AndroidInjector] for the [NewsListFragment].
     */
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeNewsListFragment(): NewsListFragment
}
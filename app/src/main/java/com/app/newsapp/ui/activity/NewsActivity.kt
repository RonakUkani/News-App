package com.app.newsapp.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.newsapp.R
import com.app.newsapp.ui.fragment.NewsListFragment
import dagger.android.support.DaggerAppCompatActivity

class NewsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        openNewsListFragment(NewsListFragment())
    }

    private fun openNewsListFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.newsContainer, fragment, fragment.javaClass.name)
            .commit()
    }


}
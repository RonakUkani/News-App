package com.app.newsapp.ui.fragment

import android.os.Bundle
import android.transition.Slide
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.app.newsapp.R
import com.app.newsapp.data.AppResponse
import com.app.newsapp.db.NewsDB
import com.app.newsapp.db.NewsEntity
import com.app.newsapp.ui.adapter.NewsListAdapter
import com.app.newsapp.utils.Constants
import com.app.newsapp.utils.isNetWorkConnection
import com.app.newsapp.utils.viewModelProvider
import com.app.newsapp.viewmodel.NewsListViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


class NewsListFragment : DaggerFragment() {
    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var db: NewsDB

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var newsList: MutableList<AppResponse.Article> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(context!!, NewsDB::class.java, Constants.KEY_NEWS_DB).build()
        newsListViewModel = viewModelProvider(viewModelFactory)
        observeData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isNetWorkConnection()) {
            callAPI()
        } else {
            getDataFromDataBase()
        }
    }


    private fun getDataFromDataBase() {
        GlobalScope.launch {
            val dbList = db.newsDao().getAllNews()
            for (i in dbList.indices) {
                newsList.add(
                    AppResponse.Article(
                        author = dbList[i].author,
                        description = dbList[i].description,
                        source = AppResponse.Article.Source(name = dbList[i].name),
                        publishedAt = dbList[i].publishedAt,
                        title = dbList[i].title,
                        urlToImage = dbList[i].urlToImage
                    )
                )
            }
        }
        setAdapter()
    }

    private fun callAPI() {
        newsListViewModel.getNewsList()
    }

    private fun observeData() {
        newsListViewModel.newsListSuccess.observe(this, Observer {
            if (it.status.equals("ok")) {
                newsList.addAll(it?.articles!!)
                GlobalScope.launch {
                    db.newsDao().deleteAllNews()
                    for (i in 0 until newsList.size) {
                        db.newsDao().saveNews(
                            NewsEntity(
                                id = (Calendar.getInstance().timeInMillis + i).toString(),
                                author = newsList[i].author,
                                description = newsList[i].description,
                                publishedAt = newsList[i].publishedAt,
                                title = newsList[i].title,
                                urlToImage = newsList[i].urlToImage,
                                name = newsList[i].source?.name
                            )
                        )
                    }
                }
                setAdapter()
            }

        })
        newsListViewModel.newsListFailure.observe(this, Observer {


        })
    }

    private fun setAdapter() {
        val adapter = NewsListAdapter(newsList) {
            openNewsDetailFragment(NewsDetailFragment.newInstance(it))
        }
        recyclerViewNewsList.adapter = adapter
        recyclerViewNewsList.layoutManager = LinearLayoutManager(this.context!!)

    }

    private fun openNewsDetailFragment(fragment: Fragment) {
        fragment.apply {
            enterTransition = Slide(Gravity.END)
            exitTransition = Slide(Gravity.START)
        }
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.add(R.id.newsContainer, fragment, fragment.javaClass.name)
            ?.addToBackStack(fragment.javaClass.name)
            ?.commit()
    }



}
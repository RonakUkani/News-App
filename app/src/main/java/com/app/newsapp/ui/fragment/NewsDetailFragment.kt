package com.app.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.app.newsapp.R
import com.app.newsapp.data.AppResponse
import com.app.newsapp.utils.convertServerDate
import com.app.newsapp.utils.loadImage
import kotlinx.android.synthetic.main.fragment_news_detail.*

class NewsDetailFragment : Fragment() {
    private val article: AppResponse.Article by lazy {
        return@lazy if (arguments?.get("ARTICLE") != null) {
            arguments?.get("ARTICLE") as AppResponse.Article
        } else {
            AppResponse.Article()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }


    companion object {
        fun newInstance(article: AppResponse.Article): NewsDetailFragment {
            val args = Bundle()
            args.putParcelable("ARTICLE", article)
            val fragment = NewsDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViewBack.setOnClickListener(this::onClick)
        imageView.setOnClickListener(this::onClick)
        setData()
    }

    private fun setData() {
        textViewNewsTitle.text = article.title
        textViewNewsCompany.text = article.source?.name!!
        textViewNewsDate.text = convertServerDate(article.publishedAt.toString())
        textViewNewsDescription.text = article.description
        imageView.loadImage(article.urlToImage)

    }

    private fun onClick(view: View) {
        when (view.id) {
            R.id.imageViewBack -> {
                activity?.onBackPressed()
            }
            R.id.imageView->{

            }
        }
    }



}
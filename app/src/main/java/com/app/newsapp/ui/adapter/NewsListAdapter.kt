package com.app.newsapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapp.R
import com.app.newsapp.data.AppResponse
import com.app.newsapp.utils.convertServerDate
import com.app.newsapp.utils.loadImage
import kotlinx.android.synthetic.main.row_news_list.view.*


class NewsListAdapter(
    var newsList: MutableList<AppResponse.Article>,
    var callback: (AppResponse.Article) -> Unit
) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_news_list, parent, false)
    )

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.imageViewNews.loadImage(newsList[position].urlToImage)

        holder.itemView.textViewNewsTitle.text = newsList[position].title
        holder.itemView.textViewNewsCompany.text = newsList[position].source?.name!!
        holder.itemView.textViewNewsDate.text =
            convertServerDate(newsList[position].publishedAt.toString())

        holder.itemView.setOnClickListener {
            callback(newsList[position])
        }

    }


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

}
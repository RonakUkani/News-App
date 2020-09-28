package com.app.newsapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NewsEntity (

    @PrimaryKey
    var id: String = "0",

    @ColumnInfo(name = "author")
    val author: String? = "",

    @ColumnInfo(name = "description")
    val description: String? = "",

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = "",

    @ColumnInfo(name = "title")
    val title: String? = "",

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? = "",

    @ColumnInfo(name = "name")
    val name: String? = ""
)
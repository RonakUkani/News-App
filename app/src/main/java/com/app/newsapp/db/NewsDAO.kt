package com.app.newsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.newsapp.utils.Constants.KEY_NEWS_DB

@Dao
interface NewsDAO {

    @Insert
    fun saveNews(book: NewsEntity)

    @Query(value = "Select * from NewsEntity")
    fun getAllNews(): List<NewsEntity>

    @Query(value = "DELETE FROM NewsEntity")
    fun deleteAllNews()

}
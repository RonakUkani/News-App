package com.app.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(NewsEntity::class)],version = 1)
abstract class NewsDB : RoomDatabase() {

    abstract fun newsDao(): NewsDAO
}
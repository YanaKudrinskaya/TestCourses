package com.yanakudrinskaya.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yanakudrinskaya.data.db.dao.FavoriteCourseDao
import com.yanakudrinskaya.data.db.entity.FavoriteCourseEntity

@Database(version = 1, entities = [FavoriteCourseEntity::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCourseDao(): FavoriteCourseDao
}

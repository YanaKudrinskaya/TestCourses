package com.yanakudrinskaya.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yanakudrinskaya.data.db.entity.FavoriteCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(course: FavoriteCourseEntity)

    @Query("SELECT * FROM favorite_courses")
    fun getFavorite(): Flow<List<FavoriteCourseEntity>>

    @Query("DELETE FROM favorite_courses WHERE id = :id")
    suspend fun removeById(id: Long)
}
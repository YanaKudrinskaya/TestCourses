package com.yanakudrinskaya.data.repository

import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.data.db.dao.FavoriteCourseDao
import com.yanakudrinskaya.data.mappers.FavoriteCourseMapper
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject internal constructor(
    private val favoriteCourseMapper: FavoriteCourseMapper,
    private val favoriteCourseDao: FavoriteCourseDao
) : FavoriteRepository {

    override suspend fun addToFavorite(course: Course) {
        favoriteCourseDao.addToFavorite(favoriteCourseMapper.map(course))
    }

    override suspend fun removeFromFavorite(courseId: Long) {
        favoriteCourseDao.removeById(courseId)
    }

    override fun getFavorite(): Flow<List<Course>> {
        return favoriteCourseDao.getFavorite().map { list ->
            list.map { favoriteCourseMapper.map(it) }.reversed()
        }
    }

    override suspend fun isFavorite(courseId: Long): Boolean {
        return favoriteCourseDao.isFavorite(courseId)
    }
}
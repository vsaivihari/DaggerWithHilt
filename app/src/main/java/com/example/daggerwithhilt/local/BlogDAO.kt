package com.example.daggerwithhilt.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlog(blog: BlogCacheEntity) : Long

    @Query("SELECT * FROM blogs")
    suspend fun get() : List<BlogCacheEntity>

}
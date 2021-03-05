package com.example.daggerwithhilt.repository

import com.example.daggerwithhilt.local.BlogDAO
import com.example.daggerwithhilt.local.CacheMapper
import com.example.daggerwithhilt.model.Blog
import com.example.daggerwithhilt.network.BlogService
import com.example.daggerwithhilt.network.NetworkMapper
import com.example.daggerwithhilt.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val blogDAO: BlogDAO,
    private val blogService: BlogService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){


    suspend fun getBlog() : Flow<DataState<List<Blog>>> = flow {
         emit(DataState.Loading)
         delay(1000)
        try {
            val networkBlog = blogService.get()
            val blogs = networkMapper.mapFromEntityList(networkBlog)
            for (blog in blogs) {
                blogDAO.insertBlog(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDAO.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }

    }
}
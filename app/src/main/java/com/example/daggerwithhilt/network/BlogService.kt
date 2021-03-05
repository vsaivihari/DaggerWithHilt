package com.example.daggerwithhilt.network

import retrofit2.http.GET

interface BlogService {

    @GET("blogs")
    suspend fun get() : List<BlogNetworkEntity>
}
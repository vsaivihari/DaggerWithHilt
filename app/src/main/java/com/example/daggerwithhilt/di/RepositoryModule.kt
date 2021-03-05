package com.example.daggerwithhilt.di

import com.example.daggerwithhilt.local.BlogDAO
import com.example.daggerwithhilt.local.CacheMapper
import com.example.daggerwithhilt.network.BlogService
import com.example.daggerwithhilt.network.NetworkMapper
import com.example.daggerwithhilt.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(blogDAO: BlogDAO,
                          blogService: BlogService,
                          cacheMapper: CacheMapper,
                          networkMapper: NetworkMapper
    ) : MainRepository {
        return MainRepository(blogDAO,blogService,cacheMapper,networkMapper)
    }
}
package com.bondstein.onlineattendanceapp.di

import com.bondstein.onlineattendanceapp.data.Repository
import com.bondstein.onlineattendanceapp.data.remote.api.APIService
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
    fun provideRepository(apiService: APIService): Repository {
        return Repository(apiService)
    }
}
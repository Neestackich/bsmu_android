package com.example.bsmuschedule.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.example.bsmuschedule.utils.networking.apiclient.APIClient
import com.example.bsmuschedule.utils.networking.apiclient.APIClientType

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLocalRepository(): APIClientType {
        return APIClient()
    }

}
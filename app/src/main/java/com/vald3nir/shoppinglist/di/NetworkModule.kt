package com.vald3nir.shoppinglist.di

import com.vald3nir.shoppinglist.repository.api.ApiService
import com.vald3nir.tolkit.networking.setup.NetworkingSetup
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://192.168.1.188:17021/"

    @Provides
    @Singleton
    fun provideApiService() = NetworkingSetup.provideApiService<ApiService>(BASE_URL)
}
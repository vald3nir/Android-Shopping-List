package com.vald3nir.shoppinglist.di

import com.vald3nir.shoppinglist.repository.api.PublicServicesAPI
import com.vald3nir.tolkit.networking.setup.NetworkingSetup
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providePublicServicesAPI() = NetworkingSetup.provideApiService<PublicServicesAPI>(baseURL = "https://world.openfoodfacts.org/")
}
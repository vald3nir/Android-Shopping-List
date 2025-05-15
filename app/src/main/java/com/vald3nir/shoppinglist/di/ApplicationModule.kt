package com.vald3nir.shoppinglist.di

import com.vald3nir.shoppinglist.domain.usecases.AuthApplicationCallImpl
import com.vald3nir.toolkit.auth.di.AuthApplicationCall
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindAuthApplicationCall(impl: AuthApplicationCallImpl): AuthApplicationCall
}
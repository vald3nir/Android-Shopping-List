package com.vald3nir.shoppinglist.di

import com.vald3nir.shoppinglist.repository.ProductsRepository
import com.vald3nir.shoppinglist.repository.ProductsRepositoryImpl
import com.vald3nir.shoppinglist.repository.ShoppingListRepository
import com.vald3nir.shoppinglist.repository.ShoppingListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindShoppingListRepository(impl: ShoppingListRepositoryImpl): ShoppingListRepository

    @Binds
    @Singleton
    abstract fun bindProductsRepository(impl: ProductsRepositoryImpl): ProductsRepository

}
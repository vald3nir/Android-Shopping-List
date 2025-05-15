package com.vald3nir.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.vald3nir.shoppinglist.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): AppDatabase = Room
        .databaseBuilder(appContext, AppDatabase::class.java, "database.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun getShoppingListDao(database: AppDatabase) = database.getShoppingListDao()

    @Provides
    @Singleton
    fun getShoppingListFakeDao(database: AppDatabase) = database.getShoppingListFakeDao()

    @Provides
    @Singleton
    fun getProductsDao(database: AppDatabase) = database.getProductsDao()

}
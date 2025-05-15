package com.vald3nir.shoppinglist.di

import android.content.Context
import com.vald3nir.toolkit.compose.designSystem.DefaultThemeColors
import com.vald3nir.toolkit.compose.designSystem.preference.ThemePreferences
import com.vald3nir.toolkit.compose.designSystem.schema.AppColorScheme
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {

    @Provides
    @Singleton
    fun provideThemePreferences(@ApplicationContext context: Context): ThemePreferences {
        return ThemePreferences(context)
    }

    @Provides
    @Singleton
    fun provideAppColorScheme(
        @ApplicationContext context: Context,
        themePreferences: ThemePreferences
    ): AppColorScheme {
        val defaultColors = DefaultThemeColors()
        return AppColorScheme(
            lightColorScheme = defaultColors.lightColors,
            darkColorScheme = defaultColors.darkColors,
            isDarkMode = themePreferences.isDarkMode(context)
        )
    }
}

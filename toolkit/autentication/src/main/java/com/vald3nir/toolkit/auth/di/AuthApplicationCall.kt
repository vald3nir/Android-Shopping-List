package com.vald3nir.toolkit.auth.di

import android.app.Activity

interface AuthApplicationCall {
    fun getUserClientID(): String
    suspend fun useFakeData()
    suspend fun clearFakeData()
    fun finishAuth(currentActivity: Activity)
}
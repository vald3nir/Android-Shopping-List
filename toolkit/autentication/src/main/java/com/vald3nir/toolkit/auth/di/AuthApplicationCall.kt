package com.vald3nir.toolkit.auth.di

import com.vald3nir.toolkit.helpers.baseclasses.BaseActivity

interface AuthApplicationCall {
    fun getUserClientID(): String
    suspend fun useFakeData()
    suspend fun clearFakeData()
    fun finishAuth(currentActivity: BaseActivity)
}
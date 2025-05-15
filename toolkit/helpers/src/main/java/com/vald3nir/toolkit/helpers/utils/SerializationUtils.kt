package com.vald3nir.toolkit.helpers.utils

import com.google.gson.Gson

fun Any.toJson() = Gson().toJson(this)

fun <T> String.fromJson(kClass: Class<T>): T = Gson().fromJson(this, kClass)
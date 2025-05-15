package com.vald3nir.tolkit.networking.handlers

sealed interface ExecutorStatus<out T> {
    data class Success<T>(val response: T): ExecutorStatus<T>
    data class Error(val message: String?): ExecutorStatus<Nothing>
}
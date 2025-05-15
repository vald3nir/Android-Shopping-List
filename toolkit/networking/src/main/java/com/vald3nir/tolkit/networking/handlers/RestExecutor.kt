package com.vald3nir.tolkit.networking.handlers

import com.google.gson.Gson
import retrofit2.HttpException

data class ErrorDTO(val message: String)

open class RestExecutor {

    suspend inline fun <reified T> execute(call: suspend () -> T?): ExecutorStatus<T?> {
        return try {

            ExecutorStatus.Success(call())

        } catch (ex: HttpException) {

            val errorBody = ex.response()?.errorBody()?.charStream()?.use {
                Gson().fromJson(it, ErrorDTO::class.java)
            }
            ExecutorStatus.Error(errorBody?.message ?: "Erro desconhecido")

        } catch (e: Exception) {
            e.printStackTrace()
            ExecutorStatus.Error("Erro desconhecido")
        }
    }
}


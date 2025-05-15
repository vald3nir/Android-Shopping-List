package com.vald3nir.tolkit.networking.handlers

import com.vald3nir.tolkit.networking.exceptions.BadRequestException
import com.vald3nir.tolkit.networking.exceptions.BodyNullException
import com.vald3nir.tolkit.networking.exceptions.ForbiddenException
import com.vald3nir.tolkit.networking.exceptions.NotFoundException
import com.vald3nir.tolkit.networking.exceptions.ServerErrorException
import com.vald3nir.tolkit.networking.exceptions.UnauthorizedException
import com.vald3nir.tolkit.networking.exceptions.UnknownApiException
import retrofit2.Response

fun <T> handlerRestResponse(response: Response<T>): T {
    if (response.isSuccessful) {
        return response.body() ?: throw BodyNullException()
    }
    when (response.code()) {
        400 -> throw BadRequestException()
        401 -> throw UnauthorizedException()
        403 -> throw ForbiddenException()
        404 -> throw NotFoundException()
        500 -> throw ServerErrorException()
        else -> throw UnknownApiException(response.code(), response.message())
    }
}
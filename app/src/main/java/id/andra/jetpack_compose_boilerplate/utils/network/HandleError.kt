/*
 * Created by Anggara Suci Nugraha on 2/24/23, 4:34 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 1/31/23, 9:54 PM
 */

package id.andra.jetpack_compose_boilerplate.utils.network

import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class Failure(
    open val httpCode: Int? = null
) : IOException() {
    data object JsonError : Failure()
    data object UnknownError : Failure()
    data object UnknownHostError : Failure()
    data object EmptyResponse : Failure()
    data object ConnectivityError : Failure()
    data object InternetError : Failure()
    data object UnAuthorizedException : Failure()
    data object ParsingDataError : Failure()
    data object IgnorableError : Failure()
    data class TimeOutError(override var message: String) : Failure()
    data class ApiError(var code: Int = 0, override var message: String) : Failure()
    data class ServerError(var code: Int = 0, override var message: String) : Failure()
    data class NotFoundException(override var message: String) : Failure()
    data class SocketTimeoutError(override var message: String) : Failure()
    data class BusinessError(override var message: String, val stackTrace: String) : Failure()
    data class HttpError(var code: Int, override var message: String) : Failure()
    data class ValidationException(override var httpCode: Int, override var message: String) :
        Failure()
}

fun Throwable.handleThrowable(): Failure {
    return if (this is HttpException && this.code() == HttpStatusCode.Unauthorized.code) {
        Failure.ApiError(message = localizedMessage.orEmpty())
    } else if (this is HttpException) {
        val response = this.response()?.errorBody()?.string()
        val message = StringBuilder()
        val errors = StringBuilder()
        response?.let {
            try {
                message.append(JSONObject(it).getString("message"))
                errors.append(JSONObject(it).getString("errors"))
            } catch (_: JSONException) {
            }
        }
        if (this.code() == HttpStatusCode.UnProcessableEntity.code)
            Failure.ValidationException(httpCode = 422, message = errors.toString())
        else
            Failure.ApiError(message = message.toString())
    } else if (this is UnknownHostException) {
        Failure.ConnectivityError
    } else if (this is SocketTimeoutException) {
        Failure.SocketTimeoutError(this.message!!)
    } else if (this.message != null) {
        Failure.NotFoundException(this.message!!)
    } else {
        Failure.UnknownError
    }
}

/*
 * Created by Anggara Suci Nugraha on 2/24/23, 4:34 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 1/8/23, 2:35 PM
 */

package id.andra.jetpack_compose_boilerplate.utils.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor(private val authProvider: AuthProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url)
        request.addHeader("Authorization", "Bearer ${authProvider.token}")
        request.addHeader("Accept", "application/json")
        Timber.d(request.toString())
        return chain.proceed(request.build())
    }
}
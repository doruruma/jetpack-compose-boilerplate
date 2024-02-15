/*
 * Created by Anggara Suci Nugraha on 2/24/23, 4:34 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 1/8/23, 8:04 PM
 */

package id.andra.jetpack_compose_boilerplate.utils.network

import id.andra.jetpack_compose_boilerplate.utils.pref.CacheManager


class AuthProvider(
    private val cacheManager: CacheManager
) {

    var token: String
        get() = cacheManager.read(TOKEN, "")
        set(value) = cacheManager.write(TOKEN, value)

    var refreshToken: String
        get() = cacheManager.read(REFRESH_TOKEN, "")
        set(value) = cacheManager.write(REFRESH_TOKEN, value)

    fun isLogin() = token.isNotEmpty()

    fun logout(callback: () -> Unit) {
        token = ""
        refreshToken = ""
        cacheManager.clearEverything(callback)
    }

    companion object {
        const val TOKEN = "pref_token"
        const val REFRESH_TOKEN = "pref_refresh_token"
    }
}
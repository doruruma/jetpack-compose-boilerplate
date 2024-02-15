/*
 * Created by Anggara Suci Nugraha on 4/5/23, 8:22 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/5/23, 8:22 PM
 */

package id.andra.jetpack_compose_boilerplate.utils.pagination

interface IPagination<T> {
    suspend fun loadNextItems()
    fun getPage(): Int
    fun resetPage()
}
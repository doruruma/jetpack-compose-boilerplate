/*
 * Created by Anggara Suci Nugraha on 4/5/23, 8:23 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/5/23, 8:23 PM
 */

package id.andra.jetpack_compose_boilerplate.utils.pagination

data class PaginationState<T>(
    val items: List<T> = emptyList(),
    val isLoading: Boolean = false,
    val isEndReached: Boolean = false
)

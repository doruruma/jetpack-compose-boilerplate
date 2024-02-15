/*
 * Created by Anggara Suci Nugraha on 4/5/23, 8:25 PM
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 4/5/23, 8:25 PM
 */

package id.andra.jetpack_compose_boilerplate.utils.pagination

import co.jasatama.courier.utils.network.Resource

class Pagination<T>(
    private val onLoadUpdated: (isLoading: Boolean, page: Int) -> Unit,
    private val onRequest: suspend (nextPage: Int) -> Resource<List<T>>,
    private val onError: suspend (message: String) -> Unit,
    private val onSuccess: (items: List<T>) -> Unit
) : IPagination<T> {

    private var page = 1

    override suspend fun loadNextItems() {
        onLoadUpdated(true, page)
        when (val result = onRequest(page)) {
            is Resource.Success -> {
                val items = result.data ?: emptyList()
                onSuccess(items)
                page++
                onLoadUpdated(false, page)
            }

            is Resource.Error -> {
                onError(result.error?.message.orEmpty())
                onLoadUpdated(false, page)
            }
        }
    }

    override fun getPage() = page

    override fun resetPage() {
        page = 1
    }
}
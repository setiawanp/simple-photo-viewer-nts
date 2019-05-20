package com.setiawanpaiman.spvnts.models

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
data class Resource<T>(
    val status: Status,
    val data: T?
) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.SUCCESS, data)
        fun <T> error(): Resource<T> = Resource(Status.ERROR, null)
        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null)
    }
}
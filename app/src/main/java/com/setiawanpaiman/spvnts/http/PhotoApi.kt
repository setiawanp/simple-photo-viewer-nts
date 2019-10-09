package com.setiawanpaiman.spvnts.http

import com.setiawanpaiman.spvnts.models.PhotoResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
interface PhotoApi {

    @GET("photos")
    suspend fun getPhotos(): Response<List<PhotoResponse>>
}
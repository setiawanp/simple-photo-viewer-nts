package com.setiawanpaiman.spvnts.repositories

import com.setiawanpaiman.spvnts.http.PhotoApi
import com.setiawanpaiman.spvnts.models.Photo
import com.setiawanpaiman.spvnts.models.Resource
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class PhotoRepository @Inject constructor(
    private val api: PhotoApi,
    private val mapper: PhotoMapper
) : PhotoDataSource {

    override suspend fun getPhotos(): Resource<List<Photo>> {
        return try {
            val result = api.getPhotos().await()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                Resource.success(mapper.map(body))
            } else {
                Resource.error()
            }
        } catch (e: Exception) {
            Resource.error()
        }
    }
}
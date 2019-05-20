package com.setiawanpaiman.spvnts.repositories

import com.setiawanpaiman.spvnts.models.Photo
import com.setiawanpaiman.spvnts.models.PhotoResponse
import javax.inject.Inject

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class PhotoMapper @Inject constructor() {

    fun map(response: List<PhotoResponse>): List<Photo> {
        return response.map {
            Photo(it.albumId, it.id, it.title, it.url, it.thumbnailUrl)
        }
    }
}
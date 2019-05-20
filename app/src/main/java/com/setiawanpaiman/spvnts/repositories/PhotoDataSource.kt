package com.setiawanpaiman.spvnts.repositories

import com.setiawanpaiman.spvnts.models.Photo
import com.setiawanpaiman.spvnts.models.Resource

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
interface PhotoDataSource {

    suspend fun getPhotos(): Resource<List<Photo>>
}
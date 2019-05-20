package com.setiawanpaiman.spvnts.ui.thumbnail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.setiawanpaiman.spvnts.ScopedViewModel
import com.setiawanpaiman.spvnts.models.Photo
import com.setiawanpaiman.spvnts.models.Resource
import com.setiawanpaiman.spvnts.repositories.PhotoRepository
import com.setiawanpaiman.spvnts.utils.CoroutineContexts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class ThumbnailViewModel @Inject constructor(
    coroutineContexts: CoroutineContexts,
    private val repository: PhotoRepository
) : ScopedViewModel(coroutineContexts) {

    private val photos = MutableLiveData<Resource<List<Photo>>>()

    fun getPhotos(): LiveData<Resource<List<Photo>>> {
        photos.postValue(Resource.loading())
        CoroutineScope(scope).launch {
            withContext(coroutineContexts.background) {
                photos.postValue(repository.getPhotos())
            }
        }
        return photos
    }
}
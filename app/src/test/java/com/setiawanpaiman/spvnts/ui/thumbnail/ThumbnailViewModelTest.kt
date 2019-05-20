package com.setiawanpaiman.spvnts.ui.thumbnail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyBlocking
import com.setiawanpaiman.spvnts.fakes.CoroutineContextsFakes
import com.setiawanpaiman.spvnts.models.Photo
import com.setiawanpaiman.spvnts.models.Resource
import com.setiawanpaiman.spvnts.repositories.PhotoRepository
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class ThumbnailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repositoryResult = Resource.success(listOf(mock<Photo> { }))
    private val repository: PhotoRepository = mock {
        onBlocking { getPhotos() } doReturn  repositoryResult
    }
    private val viewModel = ThumbnailViewModel(CoroutineContextsFakes.TEST, repository)

    @Test
    fun `get photos`() {
        val liveData = viewModel.getPhotos()
        assertEquals(repositoryResult, liveData.value)
        verifyBlocking(repository) {
            getPhotos()
        }
    }
}
package com.setiawanpaiman.spvnts.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.setiawanpaiman.spvnts.fakes.PhotoResponseFakes
import com.setiawanpaiman.spvnts.http.ApiFactory
import com.setiawanpaiman.spvnts.http.PhotoApi
import com.setiawanpaiman.spvnts.models.Photo
import com.setiawanpaiman.spvnts.models.Status
import com.setiawanpaiman.spvnts.repositories.PhotoMapper
import com.setiawanpaiman.spvnts.repositories.PhotoRepository
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class PhotoRepositoryTest {

    private val server = MockWebServer()
    private val apiFactory = ApiFactory(server.url("/").toString())
    private val data = listOf(
        Photo(1, 1, "title-1", "http://url.com/photo1", "http://url.com/thumbnail1"),
        Photo(1, 2, "title-2", "http://url.com/photo2", "http://url.com/thumbnail2")
    )
    private val mapper = mock<PhotoMapper> {
        on { map(any()) } doReturn data
    }
    private val repository = PhotoRepository(apiFactory.create(PhotoApi::class.java), mapper)

    @Before
    fun setUp() {
    }

    @Test
    fun `get photos success`() = runBlocking {
        server.enqueue(MockResponse().setBody(PhotoResponseFakes.PHOTO_RESPONSE_JSON))

        val result = repository.getPhotos()
        assertEquals(Status.SUCCESS, result.status)
        assertEquals(data, result.data)
    }

    @Test
    fun `get photos failed because of http code`() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(500).setBody(PhotoResponseFakes.PHOTO_RESPONSE_JSON))

        val result = repository.getPhotos()
        assertEquals(Status.ERROR, result.status)
        assertNull(result.data)
    }

    @Test
    fun `get photos failed because of parsing error`() = runBlocking {
        server.enqueue(MockResponse().setBody("{ abcded }"))

        val result = repository.getPhotos()
        assertEquals(Status.ERROR, result.status)
        assertNull(result.data)
    }
}
package com.setiawanpaiman.spvnts.repository

import com.setiawanpaiman.spvnts.fakes.PhotoResponseFakes
import com.setiawanpaiman.spvnts.repositories.PhotoMapper
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class PhotoMapperTest {

    private val mapper = PhotoMapper()

    @Test
    fun map() {
        val result = mapper.map(PhotoResponseFakes.generatePhotosResponse(2))
        assertEquals(2, result.size)

        assertEquals(1, result[0].albumId)
        assertEquals(1, result[0].id)
        assertEquals("title-1", result[0].title)
        assertEquals("http://url.com/photo1", result[0].url)
        assertEquals("http://url.com/thumbnail1", result[0].thumbnailUrl)

        assertEquals(1, result[1].albumId)
        assertEquals(2, result[1].id)
        assertEquals("title-2", result[1].title)
        assertEquals("http://url.com/photo2", result[1].url)
        assertEquals("http://url.com/thumbnail2", result[1].thumbnailUrl)
    }
}
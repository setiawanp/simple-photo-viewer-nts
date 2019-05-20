package com.setiawanpaiman.spvnts.http

import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class ApiFactoryTest {

    private val apiFactory = ApiFactory("http://base.url")

    @Test
    fun create() {
        assertNotNull(apiFactory.create(PhotoApi::class.java))
    }
}
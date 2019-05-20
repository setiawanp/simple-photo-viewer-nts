package com.setiawanpaiman.spvnts.util

import androidx.lifecycle.ViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.setiawanpaiman.spvnts.ui.thumbnail.ThumbnailViewModel
import com.setiawanpaiman.spvnts.utils.InjectedViewModelFactory
import org.junit.Assert.assertNotNull
import org.junit.Test
import javax.inject.Provider

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class InjectedViewModelFactoryTest {

    private val mapCreators: Map<Class<out ViewModel>, Provider<ViewModel>> = mapOf(
        ThumbnailViewModel::class.java to mock { on { get() } doReturn mock<ThumbnailViewModel> { } }
    )
    private val factory = InjectedViewModelFactory(mapCreators)

    @Test
    fun `create success`() {
        val vm = factory.create(ThumbnailViewModel::class.java)
        assertNotNull(vm)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create failed`() {
        factory.create(SampleViewModel::class.java)
    }

    class SampleViewModel : ViewModel()
}
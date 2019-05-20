package com.setiawanpaiman.spvnts.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.setiawanpaiman.spvnts.di.annotations.ViewModelKey
import com.setiawanpaiman.spvnts.ui.thumbnail.ThumbnailViewModel
import com.setiawanpaiman.spvnts.utils.InjectedViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: InjectedViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ThumbnailViewModel::class)
    abstract fun bindThumbnailViewModel(viewModel: ThumbnailViewModel): ViewModel
}
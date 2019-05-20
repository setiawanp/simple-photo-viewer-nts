package com.setiawanpaiman.spvnts.di.modules

import com.setiawanpaiman.spvnts.BuildConfig
import com.setiawanpaiman.spvnts.http.ApiFactory
import com.setiawanpaiman.spvnts.http.PhotoApi
import com.setiawanpaiman.spvnts.utils.CoroutineContexts
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApiFactory(): ApiFactory = ApiFactory(BuildConfig.BASE_URL)

    @Provides
    @Singleton
    fun providePhotoApi(apiFactory: ApiFactory) = apiFactory.create(PhotoApi::class.java)

    @Provides
    fun provideCoroutineContexts(): CoroutineContexts = CoroutineContexts(Dispatchers.Main, Dispatchers.IO)
}
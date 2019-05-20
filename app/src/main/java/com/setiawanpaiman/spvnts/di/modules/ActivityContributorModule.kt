package com.setiawanpaiman.spvnts.di.modules

import com.setiawanpaiman.spvnts.ui.thumbnail.ThumbnailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
@Module
abstract class ActivityContributorModule {

    @ContributesAndroidInjector
    abstract fun contributeThumbnailActivity(): ThumbnailActivity
}
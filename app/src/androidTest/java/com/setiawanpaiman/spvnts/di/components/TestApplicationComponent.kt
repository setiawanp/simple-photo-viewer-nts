package com.setiawanpaiman.spvnts.di.components

import com.setiawanpaiman.spvnts.MainApplication
import com.setiawanpaiman.spvnts.di.modules.ActivityContributorModule
import com.setiawanpaiman.spvnts.di.modules.TestApplicationModule
import com.setiawanpaiman.spvnts.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        TestApplicationModule::class,
        ActivityContributorModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface TestApplicationComponent : AndroidInjector<MainApplication> {
}

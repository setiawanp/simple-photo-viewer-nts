package com.setiawanpaiman.spvnts

import android.app.Activity
import android.app.Application
import com.setiawanpaiman.spvnts.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    private fun initializeInjector() {
        DaggerApplicationComponent.builder()
            .build()
            .inject(this)
    }
}
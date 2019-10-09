package com.setiawanpaiman.spvnts.http

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.setiawanpaiman.spvnts.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class ApiFactory constructor(baseUrl: String) {

    private val okHttpClient = OkHttpClient.Builder().run {
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            addInterceptor(logger)
        }
        build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    fun <T> create(klass: Class<T>): T = retrofit.create(klass)
}
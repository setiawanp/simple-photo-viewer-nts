package com.setiawanpaiman.spvnts.http

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
class ApiFactory constructor(baseUrl: String) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(OkHttpClient.Builder().build())
        .build()

    fun <T> create(klass: Class<T>): T = retrofit.create(klass)
}
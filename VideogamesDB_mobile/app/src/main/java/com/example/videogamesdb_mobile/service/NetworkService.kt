package com.example.videogamesdb_mobile.service

import com.example.videogamesdb_mobile.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal object NetworkService {
    private const val baseUrl: String = Constants.HOME_PAGE

    val data: IWebservice
        get() = retrofitConfig().create(IWebservice::class.java)

    private fun retrofitConfig(): Retrofit {
        val builder = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .writeTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build()

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
package com.pop.apptest.pr

import com.pop.apptest.CountriesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object PrService {

    private val BASE_URL = "http://10.131.71.20:3000/pr/"

    fun getService() : PrApi {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build() )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PrApi::class.java)

    }

}
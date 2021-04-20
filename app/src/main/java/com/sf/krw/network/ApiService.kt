package com.sf.krw.network

import com.sf.krw.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        private val builder = OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            addInterceptor(loggingInterceptor)
        }

        fun newInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
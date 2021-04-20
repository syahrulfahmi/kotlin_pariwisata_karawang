package com.sf.krw.network.datasource

import com.sf.krw.network.response.DetailPariwisataResponse
import com.sf.krw.network.response.HomeResponse
import com.sf.krw.network.response.PariwisataListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PariwisataDataSource {
    @GET("home")
    fun getHome(): Call<HomeResponse>

    @GET("pariwisata/{id}")
    fun getPariwisataByCategory(
        @Path("id") id: String = ""
    ): Call<PariwisataListResponse>

    @GET("pariwisata/detail/{id}")
    fun getDetail(
        @Path("id") id: Int
    ): Call<DetailPariwisataResponse>
}
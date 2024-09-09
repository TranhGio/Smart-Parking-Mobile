package com.davie.smartparking.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiSendSMS {

    @GET("api/adminphone")
    fun postPhoneNumber(
        @Query("phoneNumber") phone: String
    ): Call<UpdateStatus>


    @GET("api/tmessage")
    fun postMessage(
        @Query("message") message: String
    ): Call<SmSUpdate>
}
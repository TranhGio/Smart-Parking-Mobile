package com.davie.smartparking.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCall {

    val callApi by lazy{

        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://titan.africa/").build().create(com.davie.smartparking.data.api.ApiSendSMS::class.java)
    }
}
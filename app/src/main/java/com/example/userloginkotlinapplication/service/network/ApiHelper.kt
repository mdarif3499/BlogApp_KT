package com.example.userloginkotlinapplication.service.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  ApiHelper {
    val BASE_URL = "https://learningportal.cyclic.app/"


    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}
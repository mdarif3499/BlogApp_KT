package com.example.userloginkotlinapplication.service.network

import com.example.userloginkotlinapplication.utils.Constance.Companion.BASE_URL1
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




object  RetrofitInastans {

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



}


//companion object{
//    private  val retrofit by lazy {
//        Retrofit.Builder().baseUrl(BASE_URL1)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    val api by lazy {
//        retrofit.create(NotificationAPI::class.java)
//    }
//
//}
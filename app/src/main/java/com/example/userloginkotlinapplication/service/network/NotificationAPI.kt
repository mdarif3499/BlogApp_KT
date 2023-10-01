package com.example.userloginkotlinapplication.service.network

import com.example.userloginkotlinapplication.service.model.NotificationData
import com.example.userloginkotlinapplication.utils.Constance.Companion.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {


    @Headers("Authorization: key=$SERVER_KEY","Accept: application/json")
    @POST("fcm/send")
    suspend fun postNotification(

        @Body notification: NotificationData
    ): Response<ResponseBody>


}
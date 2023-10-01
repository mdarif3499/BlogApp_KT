package com.example.userloginkotlinapplication.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.userloginkotlinapplication.service.model.NotificationData
import com.example.userloginkotlinapplication.service.network.NotificationAPI

class NotificationRepository(val notificationApi: NotificationAPI) {
    private val liveData = MutableLiveData<String>()


    val notificationLive: LiveData<String>
        get() = liveData

    suspend fun sendNotification(notificationData: NotificationData) {
        val result = notificationApi.postNotification(notificationData)
        if (result.isSuccessful) {
            Log.e("message1", "success")

            liveData.postValue("successfull")
        } else {
            Log.e("message1", "success")


            liveData.postValue("failer")
        }


    }

}
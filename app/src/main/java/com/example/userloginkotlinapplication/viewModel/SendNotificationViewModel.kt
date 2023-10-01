package com.example.userloginkotlinapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userloginkotlinapplication.service.model.NotificationData
import com.example.userloginkotlinapplication.service.repository.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SendNotificationViewModel : ViewModel()  {
    var notificationRepository: NotificationRepository? = null

    fun sendNotification(notificationrepository: NotificationRepository,notificationData: NotificationData){

        viewModelScope.launch(Dispatchers.IO) {
          notificationrepository.sendNotification(notificationData)

        }

        notificationRepository=notificationrepository
    }


    val livedata: LiveData<String>?
        get() = notificationRepository?.notificationLive

}
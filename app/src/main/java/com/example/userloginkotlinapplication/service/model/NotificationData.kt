package com.example.userloginkotlinapplication.service.model

data class NotificationData (    val to: String,
                                 val notification: PushNotification,



                               )



data class PushNotification (val title: String,val body: String,)
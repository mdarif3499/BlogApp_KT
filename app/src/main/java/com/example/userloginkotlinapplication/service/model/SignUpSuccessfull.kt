package com.example.userloginkotlinapplication.service.model

class SignUpSuccessfull( val message: String,
                         val data: Data11,)





data class Data11(
    val name: String,
    val userName: String,
    val email: String,
    val password: String,
    val profile: String,
    val _id: String,
    val createdAt: String,
    val updatedAt: String,
    val v: Long,
)


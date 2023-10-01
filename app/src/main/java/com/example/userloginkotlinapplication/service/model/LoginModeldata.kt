package com.example.userloginkotlinapplication.service.model

data class LoginModeldata  (
    val message: String,
    val data: Aata,
    val token: String,

  )




data class Aata (
    val _id: String,
    val name: String,
    val userName: String,
    val email: String,
    val password: String,
    val profile: String,
    val createdAt: String,
    val updatedAt: String,

    val v: Long,
)

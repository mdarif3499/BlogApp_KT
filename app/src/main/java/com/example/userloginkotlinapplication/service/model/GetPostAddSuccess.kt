package com.example.userloginkotlinapplication.service.model

data class GetPostAddSuccess (
    val message: String,
    val data: Dataaa,
)

data class Dataaa (
    val title: String,
    val body: String,
    val userId: String,
    val category: List<String>,
    val photo: String,
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val v: Long,
)


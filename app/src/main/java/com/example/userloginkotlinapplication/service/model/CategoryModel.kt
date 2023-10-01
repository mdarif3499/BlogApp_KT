package com.example.userloginkotlinapplication.service.model

data class CategoryModel (
    val message: String,
    val data: List<categoryname>,
)

data class categoryname(
    val _id: String,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
    var isCheckee: Boolean=false
)


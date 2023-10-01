package com.example.userloginkotlinapplication.service.model

class SignUpError (
    val message: String,
    val error: Error,
)

data class Error(
    val errors: Errors,
    val message: String,
    val name: String,
    val message2: String,
)

data class Errors(
    val email: Email,
)

data class Email(
    val name: String,
    val message: String,
    val properties: Properties,
    val kind: String,
    val path: String,
    val value: String,
)

data class Properties(
    val message: String,
    val type: String,
    val path: String,
    val value: String,
)

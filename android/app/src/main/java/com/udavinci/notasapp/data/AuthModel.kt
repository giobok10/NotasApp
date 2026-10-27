package com.udavinci.notasapp.data

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val mensaje: String
)
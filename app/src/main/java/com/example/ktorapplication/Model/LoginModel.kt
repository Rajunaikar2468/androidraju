package com.example.ktorapplication.Model

import kotlinx.serialization.Serializable

@Serializable
data class LoginModel(val userId: String,val password: String)

package com.example.ktorapplication.Model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponce(val success: Boolean,val message: String)

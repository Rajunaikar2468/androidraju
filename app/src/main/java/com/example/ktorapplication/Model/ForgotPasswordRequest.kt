package com.example.ktorapplication.Model

import kotlinx.serialization.Serializable

@Serializable
data class ForgotPasswordRequest(val userId: String, val newPassword: String, val confirmPassword: String)

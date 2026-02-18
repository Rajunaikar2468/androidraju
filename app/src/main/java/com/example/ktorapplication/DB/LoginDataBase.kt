package com.example.ktorapplication.DB

import com.example.ktorapplication.Model.ForgotPasswordRequest
import com.example.ktorapplication.Model.LoginModel
import com.example.ktorapplication.Model.LoginResponce
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginDataBase {
    @POST("register")
    suspend fun register(
        @Body request: LoginModel
    ): Response<LoginResponce>
    @POST("login")
    suspend fun login(
        @Body request: LoginModel
    ): Response<LoginResponce>
    @POST("forgotPassword")
    suspend fun forgotPW(
        @Body request: ForgotPasswordRequest
    ): Response<LoginResponce>

}
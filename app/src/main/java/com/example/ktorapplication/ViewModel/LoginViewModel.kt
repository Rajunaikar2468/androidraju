package com.example.ktorapplication.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorapplication.DB.RetrofitClient
import com.example.ktorapplication.Model.ForgotPasswordRequest
import com.example.ktorapplication.Model.LoginModel
import com.example.ktorapplication.Model.LoginResponce
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    fun Register(userId: String,password: String,onResult: (LoginResponce?) -> Unit){
        viewModelScope.launch{
            try {
                val response = RetrofitClient.api.register(
                    LoginModel(userId, password)
                )

                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    val errorJson = response.errorBody()?.string()

                    val errorResponse = Gson().fromJson(
                        errorJson,
                        LoginResponce::class.java
                    )

                    onResult(errorResponse)
                }

            } catch (e: Exception) {
                Log.e("LOGIN", "Exception: ${e.message}")
                onResult(null)
            }

        }
    }
    fun Login (userId: String,password: String,onResult: (LoginResponce?) -> Unit){
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.login(
                    LoginModel(userId, password)
                )

                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    val errorJson = response.errorBody()?.string()

                    val errorResponse = Gson().fromJson(
                        errorJson,
                        LoginResponce::class.java
                    )

                    onResult(errorResponse)
                }

            } catch (e: Exception) {
                Log.e("LOGIN", "Exception: ${e.message}")
                onResult(null)
            }
        }
    }
    fun ForgotPassword(userId: String,newPassword: String,conformPassword: String,onResult:(LoginResponce?)-> Unit){
        viewModelScope.launch {
            try {
                val responce= RetrofitClient.api.forgotPW(ForgotPasswordRequest(userId,newPassword,conformPassword))
                if (responce.isSuccessful){
                    onResult(responce.body())
                }
                else{
                    val errorJson = responce.errorBody()?.string()

                    val errorResponse = Gson().fromJson(
                        errorJson,
                        LoginResponce::class.java
                    )

                    onResult(errorResponse)
                }
            } catch (e: Exception) {
                Log.e("LOGIN", "Exception: ${e.message}")
                onResult(null)
            }
        }

    }
}
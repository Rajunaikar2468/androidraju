package com.example.ktorapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ktorapplication.ViewModel.LoginViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var password: EditText
    private lateinit var username: EditText
    private lateinit var login: Button
    private lateinit var register: Button
    private lateinit var signUp: Button
    private lateinit var forgotPassword: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        password=findViewById(R.id.password_etx)
        username=findViewById(R.id.username_etx)
        register=findViewById(R.id.register_btn)
        login=findViewById(R.id.login_btn)
        forgotPassword =findViewById(R.id.forgotPass_txt)
        register.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.signup, null)
            val dialog = AlertDialog.Builder(this).setView(dialogView).setCancelable(true).create()
            dialog.show()
            val signUpUser = dialogView.findViewById<EditText>(R.id.signUpUser)
            val signUpPassword = dialogView.findViewById<EditText>(R.id.signUpPassword)
            val signUp = dialogView.findViewById<Button>(R.id.signUp_btn)
            signUp.setOnClickListener {
                if (!signUpUser.text.toString().isEmpty() && !signUpPassword.text.toString().isEmpty()) {
                    viewModel.Register(
                        signUpUser.text.toString(),
                        signUpPassword.text.toString()) { responce ->
                        runOnUiThread {
                            if (responce?.success == true) {
                                Toast.makeText(
                                    this,
                                    "User Register successfully",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    this,
                                    responce?.message ?: "Error",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(this,"Please Enter the User name and password", Toast.LENGTH_SHORT).show()
                }

            }

        }
        login.setOnClickListener {
            viewModel.Login(username.text.toString(), password.text.toString()) { responce ->
                runOnUiThread {
                    if (responce?.success == true) {
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, responce?.message?:"Error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }
        forgotPassword.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.updatepassword, null)
            val dialog = AlertDialog.Builder(this).setView(dialogView).setCancelable(true).create()
            dialog.show()
            val updateUserName = dialogView.findViewById<EditText>(R.id.updateUserName)
            val newPassword = dialogView.findViewById<EditText>(R.id.newPassword)
            val confirmPassword = dialogView.findViewById<EditText>(R.id.conformPassword)
            val updatePW = dialogView.findViewById<Button>(R.id.updatePw_btn)
            updatePW.setOnClickListener {
                viewModel.ForgotPassword(
                    updateUserName.text.toString(), newPassword.text.toString(), confirmPassword.text.toString()) { responce ->
                    runOnUiThread {
                        if (responce?.success == true) {
                            Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        } else {
                            Toast.makeText(this, responce?.message ?: "Error", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

            }


        }
    }
}
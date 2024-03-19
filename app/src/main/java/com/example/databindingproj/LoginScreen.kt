package com.example.databindingproj

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databindingproj.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {

    lateinit var loginBinding : ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen)
        loginBinding.editTextTextEmailAddress
        loginBinding.editTextTextPassword
        loginBinding.loginButton.setOnClickListener {

            val userName = loginBinding.editTextTextEmailAddress.text.toString();
            val password = loginBinding.editTextTextPassword.text.toString();

            System.out.println("Password: $password");

            if (Patterns.EMAIL_ADDRESS.matcher(userName).matches() && password.isNotEmpty()) {

                Toast.makeText(applicationContext,"Logged in successfully.",Toast.LENGTH_SHORT).show()
            }

        }

        loginBinding.register.setOnClickListener {

            startActivity(Intent(this@LoginScreen, RegisterScreen::class.java))

        }

    }

}
package com.example.watchmovies.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.watchmovies.Movies.Home
import com.example.watchmovies.R
import com.example.watchmovies.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    fun setup(){
        onLoginButtonClick()
        moveToRegisterScreen()
    }

    private fun onLoginButtonClick(){
        binding.loginActionButton.setOnClickListener {
            when {
                !emailValidator(binding.loginEmailField.text.toString()) -> binding.loginErrorMessage.text =
                    getString(R.string.invalidEmail)
                !passwordValidator(binding.loginPasswordField.text.toString()) -> binding.loginErrorMessage.text =
                    getString(R.string.shortPassword)
                else -> {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        binding.loginEmailField.text.toString(),
                        binding.loginPasswordField.text.toString()
                    ).addOnCompleteListener(
                        OnCompleteListener<AuthResult> { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(this@Login, Home::class.java)
                                startActivity(intent)
                            } else {
                                binding.loginErrorMessage.text =
                                    getString(R.string.invalidPassword)
                            }
                        }
                    )
                }
            }
        }
    }

    private fun moveToRegisterScreen(){
        binding.loginGoToRegister.setOnClickListener {
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
            finish()
        }
    }
}

fun emailValidator(email: String): Boolean {
    return email.trim().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun passwordValidator(password: String): Boolean {
    return password.trim().length >= 8
}
package com.example.watchmovies.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.watchmovies.Movies.Home
import com.example.watchmovies.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailField = findViewById<EditText>(R.id.login_emailField)
        val passwordField = findViewById<EditText>(R.id.login_passwordField)
        val errorField = findViewById<TextView>(R.id.login_errorMessage)
        val loginActionButton = findViewById<Button>(R.id.login_actionButton)

        loginActionButton.setOnClickListener {
            when {
                !emailValidator(emailField.text.toString()) -> errorField.text =
                    "Please enter an valid email."
                !passwordValidator(passwordField.text.toString()) -> errorField.text =
                    "Password must have at least 8 characters"
                else -> {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        emailField.text.toString(),
                        passwordField.text.toString()
                    ).addOnCompleteListener(
                        OnCompleteListener<AuthResult> { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(this@Login, Home::class.java)
                                startActivity(intent)
                            } else {
                                errorField.text =
                                    "The password is invalid or the user does not have a password."
                            }
                        }
                    )
                }
            }
        }
        // Move to Register Screen
        val registerScreenButton = findViewById<TextView>(R.id.login_goToRegister)
        registerScreenButton.setOnClickListener {
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
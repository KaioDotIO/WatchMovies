package com.example.watchmovies.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.watchmovies.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val emailField = findViewById<EditText>(R.id.register_emailField)
        val passwordField = findViewById<EditText>(R.id.register_passwordField)
        val errorField = findViewById<TextView>(R.id.register_errorMessage)
        val registerActionButton = findViewById<Button>(R.id.register_actionButton)

        registerActionButton.setOnClickListener {
            when {
                !emailValidator(emailField.text.toString()) -> errorField.text =
                    "Please enter an valid email."
                !passwordValidator(passwordField.text.toString()) -> errorField.text =
                    "Password must have at least 8 characters"
                else -> {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        emailField.text.toString(),
                        passwordField.text.toString()
                    ).addOnCompleteListener(
                        OnCompleteListener<AuthResult> { task ->
                            if (task.isSuccessful) {
                                errorField.text = "successfully registered."
                            } else {
                                errorField.text = "E-mail already registered by another user."
                            }
                        }
                    )
                }
            }
        }
        // Move to Login Screen
        val loginScreenButton = findViewById<TextView>(R.id.register_goToLogin)
        loginScreenButton.setOnClickListener {
            val intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
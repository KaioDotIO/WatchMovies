package com.example.watchmovies.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.watchmovies.R
import com.example.watchmovies.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        onRegisterButtonClick()
        moveToLoginScreen()
    }

    private fun onRegisterButtonClick() {
        binding.registerActionButton.setOnClickListener {
            when {
                !emailValidator(binding.registerEmailField.text.toString()) -> binding.registerErrorMessage.text =
                    getString(R.string.invalidEmail)
                !passwordValidator(binding.registerPasswordField.text.toString()) -> binding.registerErrorMessage.text =
                    getString(R.string.shortPassword)
                else -> {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.registerEmailField.text.toString(),
                        binding.registerPasswordField.text.toString()
                    ).addOnCompleteListener(
                        OnCompleteListener<AuthResult> { task ->
                            if (task.isSuccessful) {
                                binding.registerErrorMessage.text =
                                    getString(R.string.successRegstration)
                            } else {
                                binding.registerErrorMessage.text =
                                    getString(R.string.alreadyRegisteredUser)
                            }
                        }
                    )
                }
            }
        }
    }

    private fun moveToLoginScreen() {
        val loginScreenButton = findViewById<TextView>(R.id.register_goToLogin)
        loginScreenButton.setOnClickListener {
            val intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}


package com.example.tarifjasakalibrasi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    // Kredensial default (dalam produksi gunakan database atau API)
    private val defaultUsername = "admin"
    private val defaultPassword = "password123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        setupLogin()
    }

    private fun initViews() {
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
    }

    private fun setupLogin() {
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInput(username, password)) {
                if (isValidCredentials(username, password)) {
                    // Login berhasil
                    val intent = Intent(this, ServiceListActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Username atau password salah!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        return when {
            username.isEmpty() -> {
                etUsername.error = "Username tidak boleh kosong"
                false
            }
            password.isEmpty() -> {
                etPassword.error = "Password tidak boleh kosong"
                false
            }
            else -> true
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return username == defaultUsername && password == defaultPassword
    }
}
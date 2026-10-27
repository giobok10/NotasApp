package com.udavinci.notasapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.udavinci.notasapp.R
import com.udavinci.notasapp.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvError: TextView
    private lateinit var progressBarLogin: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvError = findViewById(R.id.tvError)
        // Nota: Asegúrate de tener un ProgressBar con id progressBarLogin en tu activity_login.xml si deseas mostrar carga visual, o puedes omitirlo.

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                tvError.text = "Por favor completa todos los campos"
                tvError.visibility = View.VISIBLE
            } else {
                tvError.visibility = View.GONE
                authViewModel.login(email, password)
            }
        }

        // Observar el resultado del Login reactivamente mediante LiveData
        authViewModel.loginResult.observe(this) { result ->
            result.onSuccess {
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("USER_EMAIL", etEmail.text.toString().trim())
                startActivity(intent)
                finish()
            }.onFailure { exception ->
                tvError.text = exception.message ?: "Error de autenticación"
                tvError.visibility = View.VISIBLE
            }
        }
    }
}
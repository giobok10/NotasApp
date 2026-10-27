package com.udavinci.notasapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.udavinci.notasapp.R
import android.content.Intent

class DashboardActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var btnCrearNota: Button
    private lateinit var btnVerNotas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tvWelcome = findViewById(R.id.tvWelcome)
        btnCrearNota = findViewById(R.id.btnCrearNota)
        btnVerNotas = findViewById(R.id.btnVerNotas)

        // Obtener el nombre del usuario enviado por Intent (o correo)
        val userEmail = intent.getStringExtra("USER_EMAIL") ?: "Usuario"
        tvWelcome.text = "¡Bienvenido, $userEmail!"

        // Acciones de los botones (las conectaremos en las siguientes fases)
        btnCrearNota.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivity(intent)
        }

        btnVerNotas.setOnClickListener {
            val intent = Intent(this, NoteListActivity::class.java)
            startActivity(intent)
        }
    }
}
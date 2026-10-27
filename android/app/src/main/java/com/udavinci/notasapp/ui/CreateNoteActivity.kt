package com.udavinci.notasapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.udavinci.notasapp.R
import com.udavinci.notasapp.viewmodel.NotaViewModel

class CreateNoteActivity : AppCompatActivity() {

    private val notaViewModel: NotaViewModel by viewModels()

    private lateinit var etNoteTitle: EditText
    private lateinit var etNoteContent: EditText
    private lateinit var btnSaveNote: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        etNoteTitle = findViewById(R.id.etNoteTitle)
        etNoteContent = findViewById(R.id.etNoteContent)
        btnSaveNote = findViewById(R.id.btnSaveNote)
        progressBar = findViewById(R.id.progressBar)

        btnSaveNote.setOnClickListener {
            val title = etNoteTitle.text.toString().trim()
            val content = etNoteContent.text.toString().trim()

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Llamar al ViewModel para enviar la nota al backend en C#
                notaViewModel.crearNota(title, content)
            }
        }

        // Observar el estado de carga
        notaViewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            btnSaveNote.isEnabled = !isLoading
        }

        // Observar el estatus de la operación (éxito o error)
        notaViewModel.operationStatus.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "¡Nota creada con éxito!", Toast.LENGTH_SHORT).show()
                finish() // Regresar al dashboard o listado
            } else {
                Toast.makeText(this, "Error al guardar la nota en el servidor", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
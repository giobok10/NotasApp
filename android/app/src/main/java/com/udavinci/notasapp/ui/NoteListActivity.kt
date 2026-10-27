package com.udavinci.notasapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udavinci.notasapp.R
import com.udavinci.notasapp.viewmodel.NotaViewModel

class NoteListActivity : AppCompatActivity() {

    private val notaViewModel: NotaViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBarList: ProgressBar
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        recyclerView = findViewById(R.id.recyclerViewNotes)
        progressBarList = findViewById(R.id.progressBarList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        noteAdapter = NoteAdapter(emptyList())
        recyclerView.adapter = noteAdapter

        // Observar la lista de notas
        notaViewModel.notas.observe(this) { notas ->
            noteAdapter.updateNotes(notas)
        }

        // Observar el estado de carga
        notaViewModel.isLoading.observe(this) { isLoading ->
            progressBarList.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Cargar las notas del backend al iniciar la actividad
        notaViewModel.fetchNotas()
    }
}
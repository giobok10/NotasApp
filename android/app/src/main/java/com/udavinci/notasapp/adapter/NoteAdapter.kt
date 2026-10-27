package com.udavinci.notasapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udavinci.notasapp.R
import com.udavinci.notasapp.data.NotaModel

class NoteAdapter(private var notas: List<NotaModel>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNoteTitle: TextView = view.findViewById(R.id.tvNoteTitle)
        val tvNoteContent: TextView = view.findViewById(R.id.tvNoteContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val nota = notas[position]
        holder.tvNoteTitle.text = nota.titulo
        holder.tvNoteContent.text = nota.contenido
    }

    override fun getItemCount() = notas.size

    fun updateNotes(newNotas: List<NotaModel>) {
        notas = newNotas
        notifyDataSetChanged()
    }
}
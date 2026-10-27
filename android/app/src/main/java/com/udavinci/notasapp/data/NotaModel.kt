package com.udavinci.notasapp.data

data class NotaModel(
    val id: Int,
    val titulo: String,
    val contenido: String,
    val fechaCreacion: String
)

data class NotaRequest(
    val titulo: String,
    val contenido: String
)
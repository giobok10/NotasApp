package com.udavinci.notasapp.data

class NotaRepository {
    private val apiService = ApiClient.retrofitService

    suspend fun login(request: LoginRequest) = apiService.login(request)

    val notasLiveData = mutableListOf<NotaModel>()

    suspend fun getNotas() = apiService.getNotas()

    suspend fun crearNota(request: NotaRequest) = apiService.crearNota(request)
}
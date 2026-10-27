package com.udavinci.notasapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udavinci.notasapp.data.NotaModel
import com.udavinci.notasapp.data.NotaRequest
import com.udavinci.notasapp.data.NotaRepository
import kotlinx.coroutines.launch

class NotaViewModel : ViewModel() {
    private val repository = NotaRepository()

    private val _notas = MutableLiveData<List<NotaModel>>()
    val notas: LiveData<List<NotaModel>> get() = _notas

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _operationStatus = MutableLiveData<Boolean>()
    val operationStatus: LiveData<Boolean> get() = _operationStatus

    fun fetchNotas() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getNotas()
                if (response.isSuccessful) {
                    _notas.postValue(response.body() ?: emptyList())
                }
            } catch (_: Exception) {
                _notas.postValue(emptyList())
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun crearNota(titulo: String, contenido: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.crearNota(NotaRequest(titulo, contenido))
                _operationStatus.postValue(response.isSuccessful)
            } catch (_: Exception) {
                _operationStatus.postValue(false)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
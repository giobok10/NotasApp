package com.udavinci.notasapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udavinci.notasapp.data.ApiClient
import com.udavinci.notasapp.data.LoginRequest
import com.udavinci.notasapp.data.NotaRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = NotaRepository()

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(LoginRequest(email, password))
                if (response.isSuccessful && response.body() != null) {
                    val token = response.body()!!.token
                    ApiClient.authToken = token // Guardamos el token para peticiones futuras[cite: 4]
                    _loginResult.postValue(Result.success(token))
                } else {
                    _loginResult.postValue(Result.failure(Exception("Credenciales inválidas o error en el servidor")))
                }
            } catch (e: Exception) {
                _loginResult.postValue(Result.failure(e))
            }
        }
    }
}
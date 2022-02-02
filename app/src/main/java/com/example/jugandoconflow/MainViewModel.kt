package com.example.jugandoconflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MainViewModel : ViewModel(){

    private val _loginUiStates = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState: StateFlow<LoginUiState> = _loginUiStates


    fun login(username: String, password: String) = viewModelScope.launch {
        _loginUiStates.value = LoginUiState.Loading
        delay(2000L)
        if(username == "android" && password == "123"){
            _loginUiStates.value = LoginUiState.Success
        }else{
            _loginUiStates.value = LoginUiState.Error("Wrong credentials")
        }
    }

    sealed class LoginUiState{
        object Success: LoginUiState()
        data class Error(val message: String): LoginUiState()
        object Loading: LoginUiState()
        object Empty: LoginUiState()
    }

}
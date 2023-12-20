package com.dicoding.soulsupport.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.soulsupport.data.model.AuthModel
import com.dicoding.soulsupport.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository): ViewModel() {
    fun loginUser(email: String,password: String) = repository.login(email,password)

    fun saveToken(autModel: AuthModel) {
        viewModelScope.launch {
            repository.saveToken(autModel)
        }
    }

}
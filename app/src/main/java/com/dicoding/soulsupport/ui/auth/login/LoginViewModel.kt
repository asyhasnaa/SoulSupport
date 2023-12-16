package com.dicoding.soulsupport.ui.auth.login

import androidx.lifecycle.ViewModel
import com.dicoding.soulsupport.data.repository.AuthRepository

class LoginViewModel(private val repository: AuthRepository): ViewModel() {
    fun loginUser(email: String,password: String) = repository.login(email,password)
}
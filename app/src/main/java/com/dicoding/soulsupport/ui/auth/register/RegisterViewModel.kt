package com.dicoding.soulsupport.ui.auth.register

import androidx.lifecycle.ViewModel
import com.dicoding.soulsupport.data.repository.AuthRepository

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {
    fun registerUser(name: String, email: String,password: String,confirmPassword: String) =
        repository.register(name, email, password, confirmPassword)
}
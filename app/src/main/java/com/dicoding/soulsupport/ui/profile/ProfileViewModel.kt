package com.dicoding.soulsupport.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.soulsupport.data.Result
import com.dicoding.soulsupport.data.remote.response.User
import com.dicoding.soulsupport.data.repository.AuthRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: AuthRepository): ViewModel() {
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

}
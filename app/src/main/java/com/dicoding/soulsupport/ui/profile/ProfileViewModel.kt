package com.dicoding.soulsupport.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.soulsupport.data.repository.AuthRepository
import kotlinx.coroutines.launch
import com.dicoding.soulsupport.data.Result
import com.dicoding.soulsupport.data.model.AuthModel

class ProfileViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> get() = _userName

    private val _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> get() = _userEmail



    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}

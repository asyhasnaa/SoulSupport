package com.dicoding.soulsupport.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.soulsupport.data.model.AuthModel
import com.dicoding.soulsupport.data.repository.AuthRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AuthRepository): ViewModel() {

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> get() = _userName

    private suspend fun fetchUserInfo() {
        repository.getUserInfo().collect { authModel ->
            _userName.postValue(authModel.name)
        }
    }

    init {
        viewModelScope.launch {
            fetchUserInfo()
        }
    }

   fun getUser(): LiveData<AuthModel> {
       return repository.getUser().asLiveData()
   }
}
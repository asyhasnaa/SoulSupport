package com.dicoding.soulsupport.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.soulsupport.data.model.AuthModel
import com.dicoding.soulsupport.data.repository.AuthRepository

class MainViewModel(private val repository: AuthRepository): ViewModel() {

   fun getUser(): LiveData<AuthModel> {
       return repository.getUser().asLiveData()
   }
}
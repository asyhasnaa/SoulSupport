package com.dicoding.soulsupport.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.soulsupport.data.pref.AuthPreferences
import kotlinx.coroutines.launch

class DarkModeViewModel(private val authPreferences: AuthPreferences): ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return authPreferences.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            authPreferences.saveThemeSetting(isDarkModeActive)
        }
    }
}
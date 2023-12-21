package com.dicoding.soulsupport.ui


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.soulsupport.data.repository.AuthRepository
import com.dicoding.soulsupport.di.Injection
import com.dicoding.soulsupport.ui.auth.login.LoginViewModel
import com.dicoding.soulsupport.ui.auth.register.RegisterViewModel
import com.dicoding.soulsupport.ui.main.MainViewModel
import com.dicoding.soulsupport.ui.profile.ProfileViewModel

class ViewModelFactory(
    private val repository: AuthRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when (modelClass) {
            RegisterViewModel::class.java -> RegisterViewModel(repository)
            LoginViewModel::class.java -> LoginViewModel(repository)
            MainViewModel::class.java -> MainViewModel(repository)
            ProfileViewModel::class.java -> ProfileViewModel(repository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        } as T


    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}
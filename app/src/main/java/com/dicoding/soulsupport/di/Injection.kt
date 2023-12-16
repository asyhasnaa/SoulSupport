package com.dicoding.soulsupport.di

import android.content.Context
import com.dicoding.soulsupport.data.remote.refrofit.ApiConfig
import com.dicoding.soulsupport.data.repository.AuthRepository

object Injection {
    fun provideRepository(context: Context): AuthRepository {
        val apiService = ApiConfig.getApiService()
        return AuthRepository.getInstance(apiService)
    }
}
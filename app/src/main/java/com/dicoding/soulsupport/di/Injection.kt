package com.dicoding.soulsupport.di

import android.content.Context
import android.util.Log
import com.dicoding.soulsupport.data.pref.AuthPreferences
import com.dicoding.soulsupport.data.pref.dataStore
import com.dicoding.soulsupport.data.remote.refrofit.ApiConfig
import com.dicoding.soulsupport.data.repository.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): AuthRepository {
        val pref = AuthPreferences.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        Log.d("STATUS TOKEN", user.token.toString())
        val apiService = ApiConfig.getApiService()
        return AuthRepository.getInstance(pref,apiService)
    }
}
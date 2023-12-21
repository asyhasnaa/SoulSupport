package com.dicoding.soulsupport.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.soulsupport.data.Result
import com.dicoding.soulsupport.data.model.AuthModel
import com.dicoding.soulsupport.data.pref.AuthPreferences
import com.dicoding.soulsupport.data.remote.refrofit.ApiService
import com.dicoding.soulsupport.data.remote.response.ErrorResponse
import com.dicoding.soulsupport.data.remote.response.LoginResponse
import com.dicoding.soulsupport.data.remote.response.RegisterResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class AuthRepository(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
) {
    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(name, email, password, confirmPassword)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            Log.d("Repository", "register user: $errorMessage ")
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            Log.d("Repository", "login user: $errorMessage ")
            emit(Result.Error(errorMessage.toString()))
        }
    }

    suspend fun saveToken(token: AuthModel) {
        authPreferences.saveToken(token)
    }

    fun getUser(): Flow<AuthModel> {
        return authPreferences.getSession()
    }

    fun getUserInfo(): Flow<AuthModel> {
        return getUser()
    }

    suspend fun logout() {
        authPreferences.logout()
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            authPreferences: AuthPreferences,
            apiService: ApiService
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(apiService, authPreferences)
            }.also { instance = it }
    }
}
package com.dicoding.soulsupport.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.soulsupport.data.remote.refrofit.ApiService
import com.dicoding.soulsupport.data.remote.response.RegisterResponse
import com.dicoding.soulsupport.data.Result
import com.dicoding.soulsupport.data.remote.response.LoginResponse
import retrofit2.HttpException

class AuthRepository(private val apiService: ApiService) {

    fun register(name:String,email:String,password: String,confirmPassword: String): LiveData<Result<RegisterResponse>> = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.register(name,email,password,confirmPassword)
            emit(Result.Success(response))
        }catch (e: HttpException) {
            error(Result.Error(" Register gagal"))
        }
    }

    fun login(email: String,password: String) : LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            emit(Result.Success(response))
        }catch (e: HttpException) {
            error(Result.Error("Login Gagal"))
        }
    }




    companion object {
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            apiService: ApiService
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(apiService)
            }.also { instance = it }
    }
}
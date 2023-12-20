package com.dicoding.soulsupport.data.remote.refrofit

import com.dicoding.soulsupport.data.model.ChatRequest
import com.dicoding.soulsupport.data.model.ChatResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApiService {
    @POST("/predict")
    fun sendMessage(@Body request: ChatRequest): Call<ChatResponse>
}

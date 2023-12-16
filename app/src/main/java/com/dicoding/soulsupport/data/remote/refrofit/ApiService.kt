package com.dicoding.soulsupport.data.remote.refrofit

import com.dicoding.soulsupport.data.remote.response.Data
import com.dicoding.soulsupport.data.remote.response.DetailUserResponse
import com.dicoding.soulsupport.data.remote.response.LoginResponse
import com.dicoding.soulsupport.data.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("age") age: String,
    ) : RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse

    @GET("/user{id}")
    suspend fun getUserId(
        @Path("id") id: Int,
    ) : DetailUserResponse<Data>

    @FormUrlEncoded
    @POST("predict")
    fun sendMessage(@Field("message") message: String): retrofit2.Call<String>
}
package com.dicoding.soulsupport.data.remote.refrofit

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("age") age: String,
    )

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    )

    @GET("profile/{id}")
    suspend fun getIdUser(
        @Header("Authorization") token: String,
        @Path("id") userId: String
    )
}
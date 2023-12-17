package com.dicoding.soulsupport.data.model

data class AuthModel(
    val email: String?,
    val token: String?,
    val isLogin: Boolean = false
)
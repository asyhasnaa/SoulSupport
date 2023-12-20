package com.dicoding.soulsupport.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("Success")
	val success: Boolean? = null
)

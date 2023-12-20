package com.dicoding.soulsupport.data.remote.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

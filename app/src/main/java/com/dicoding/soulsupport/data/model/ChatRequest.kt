package com.dicoding.soulsupport.data.model

data class ChatRequest(
    val message: String
)

data class ChatResponse(
    val prediction: String
)

data class ChatMessage(
    val text: String,
    val type: Int
)
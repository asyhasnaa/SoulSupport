package com.dicoding.soulsupport.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.data.model.ChatRequest
import com.dicoding.soulsupport.data.model.ChatResponse
import com.dicoding.soulsupport.data.remote.refrofit.ApiClient
import com.dicoding.soulsupport.databinding.ActivityChatBinding
import com.dicoding.soulsupport.ui.adapter.ChatAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatActivity : AppCompatActivity() {

    private val apiService = ApiClient.create()
    private lateinit var binding: ActivityChatBinding
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.item_chat)
        chatAdapter = ChatAdapter()
        recyclerView.adapter = chatAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        onBack()

        binding.sendButton.setOnClickListener {
            val userMessage = binding.edtMessage.text.toString().trim()

            if (userMessage.isNotEmpty()) {
                chatAdapter.addUserMessage(userMessage)
                binding.edtMessage.text.clear()

                sendMessage(userMessage)
            }
        }
    }

    private fun onBack() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun sendMessage(message: String) {
        val request = ChatRequest(message)
        val call = apiService.sendMessage(request)

        call.enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                if (response.isSuccessful) {
                    val prediction = response.body()?.prediction ?: "No prediction available"
                    runOnUiThread {
                        showBotResponse(prediction)
                    }
                } else {
                    runOnUiThread {
                        showBotResponse("Failed to get prediction. Error code: ${response.code()}")
                    }
                }
            }


            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                showBotResponse("Failed to connect to the server. Error: ${t.message}")
            }

        })
    }

    private fun showBotResponse(response: String) {
        chatAdapter.addBotMessage(response)
    }
}

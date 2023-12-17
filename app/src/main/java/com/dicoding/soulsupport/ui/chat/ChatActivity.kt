package com.dicoding.soulsupport.ui.chat

import ChatViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.data.model.ChatMessage
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
    private lateinit var chatViewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.item_chat)
        chatAdapter = ChatAdapter()
        recyclerView.adapter = chatAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        chatViewModel.initSharedPreferences(this)

        onBack()

        binding.sendButton.setOnClickListener {
            val userMessage = binding.edtMessage.text.toString().trim()

            if (userMessage.isNotEmpty()) {
                chatAdapter.addUserMessage(userMessage)
                binding.edtMessage.text.clear()

                sendMessage(userMessage)
            }
        }
        chatViewModel.chatHistory.value?.let {
            chatAdapter.setMessages(it)
        }

    }

    private fun onBack() {
        binding.btnBack.setOnClickListener {
            onBackPressed()

        }
    }

    private fun sendMessage(userMessage: String) {

        binding.edtMessage.text.clear()

        val request = ChatRequest(userMessage)
        val call = apiService.sendMessage(request)

        call.enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                if (response.isSuccessful) {
                    val prediction = response.body()?.prediction ?: "No prediction available"
                    runOnUiThread {

                        chatAdapter.addBotMessage(prediction)

                        chatViewModel.chatHistory.value?.let {
                            it.add(ChatMessage(userMessage, ChatAdapter.USER))
                            it.add(ChatMessage(prediction, ChatAdapter.BOT))
                            chatViewModel.chatHistory.value = it
                            chatViewModel.saveChatHistory()
                        }
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
        chatAdapter.addUserMessage(response)
        chatAdapter.addBotMessage(response)
    }

    override fun onDestroy() {
        super.onDestroy()
            chatViewModel.saveChatHistory()
    }
}

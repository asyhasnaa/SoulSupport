import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.soulsupport.data.model.ChatMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChatViewModel : ViewModel() {
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    val chatHistory = MutableLiveData<MutableList<ChatMessage>>(mutableListOf())

    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("ChatHistory", Context.MODE_PRIVATE)
        val savedChatHistoryJson = sharedPreferences.getString("chatHistory", null)
        savedChatHistoryJson?.let {
            val type = object : TypeToken<MutableList<ChatMessage>>() {}.type
            chatHistory.value = gson.fromJson(it, type)
        }
    }

    fun saveChatHistory() {
        val chatHistoryJson = gson.toJson(chatHistory.value)
        sharedPreferences.edit().putString("chatHistory", chatHistoryJson).apply()
    }
}

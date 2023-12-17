package com.dicoding.soulsupport.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.data.model.ChatMessage

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    private val messages: MutableList<ChatMessage> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MessageViewHolder(
            inflater.inflate(R.layout.item_message, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int = messages.size

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userMessageTextView: TextView = itemView.findViewById(R.id.cht_user_text)
        private val botMessageTextView: TextView = itemView.findViewById(R.id.cht_bot_text)

        fun bind(message: ChatMessage) {
            if (message.type == USER) {
                userMessageTextView.visibility = View.VISIBLE
                botMessageTextView.visibility = View.GONE

                userMessageTextView.text = message.text
            } else {
                userMessageTextView.visibility = View.GONE
                botMessageTextView.visibility = View.VISIBLE

                botMessageTextView.text = message.text
                val backgroundDrawable = R.drawable.receive_box
                val backgroundTint = R.color.secondary_400

                botMessageTextView.setBackgroundResource(backgroundDrawable)
                botMessageTextView.backgroundTintList = ContextCompat.getColorStateList(itemView.context, backgroundTint)
            }
        }
    }

    fun addUserMessage(message: String) {
        messages.add(ChatMessage(message, USER))
        notifyItemInserted(messages.size - 1)
    }

    fun addBotMessage(message: String) {
        messages.add(ChatMessage(message, BOT))
        notifyItemInserted(messages.size - 1)
    }

    fun setMessages(messages: List<ChatMessage>) {
        this.messages.clear()
        this.messages.addAll(messages)
        notifyDataSetChanged()
    }


    companion object {
        const val USER = 1
        const val BOT = 2
    }
}

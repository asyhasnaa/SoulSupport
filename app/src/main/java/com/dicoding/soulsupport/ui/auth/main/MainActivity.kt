package com.dicoding.soulsupport.ui.auth.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.databinding.ActivityMainBinding
import com.dicoding.soulsupport.ui.auth.article.ArticleActivity
import com.dicoding.soulsupport.ui.auth.chat.ChatActivity
import com.dicoding.soulsupport.ui.auth.meditation.MeditationActivity
import com.dicoding.soulsupport.ui.auth.note.NoteActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clickListener()

    }

    private fun clickListener() {
        val menuChat = findViewById<CardView>(R.id.Menu1)
        val menuNote = findViewById<CardView>(R.id.Menu2)
        val menuArticle = findViewById<CardView>(R.id.Menu3)
        val menuMeditation = findViewById<CardView>(R.id.Menu4)

        menuChat.setOnClickListener {
            startActivity(Intent(this@MainActivity, ChatActivity::class.java))
        }
        menuNote.setOnClickListener {
            startActivity(Intent(this@MainActivity, NoteActivity::class.java))
        }
        menuArticle.setOnClickListener {
            startActivity(Intent(this@MainActivity, ArticleActivity::class.java))
        }
        menuMeditation.setOnClickListener {
            startActivity(Intent(this@MainActivity, MeditationActivity::class.java))
        }

    }

}
package com.dicoding.soulsupport.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.databinding.ActivityMainBinding
import com.dicoding.soulsupport.ui.article.ArticleActivity
import com.dicoding.soulsupport.ui.chat.ChatActivity
import com.dicoding.soulsupport.ui.meditation.MeditationActivity
import com.dicoding.soulsupport.ui.note.note.NoteActivity
import com.dicoding.soulsupport.ui.profile.ProfileActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clickListener()
        bottomNavigation()

    }

    private fun bottomNavigation() {
        val bottomAppBar = findViewById<BottomAppBar>(R.id.bottomAppBar)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    startActivity(Intent(this@MainActivity, MainActivity::class.java))
                }
                R.id.bottom_note -> {
                    startActivity(Intent(this@MainActivity, NoteActivity::class.java))
                }
                R.id.bottom_profile -> {
                    startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                }
            }
            true
        }
    }

    private fun clickListener() {
        val menuChat = findViewById<CardView>(R.id.Menu1)
        val menuNote = findViewById<CardView>(R.id.Menu2)
        val menuMeditation = findViewById<CardView>(R.id.Menu3)
        val menuArticle = findViewById<CardView>(R.id.Menu4)

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
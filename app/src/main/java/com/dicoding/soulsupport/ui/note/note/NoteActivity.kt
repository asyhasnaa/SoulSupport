package com.dicoding.soulsupport.ui.note.note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.soulsupport.databinding.ActivityNoteBinding
import com.dicoding.soulsupport.ui.note.detail.DetailNoteActivity

class NoteActivity : AppCompatActivity() {

     private lateinit var binding: ActivityNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, DetailNoteActivity::class.java)
            startActivity(intent)
        }
    }
}
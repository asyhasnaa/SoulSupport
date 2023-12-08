package com.dicoding.soulsupport.ui.note.note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.soulsupport.databinding.ActivityNoteBinding
import com.dicoding.soulsupport.ui.main.MainActivity
import com.dicoding.soulsupport.ui.note.add.AddUpdateNoteActivity

class NoteActivity : AppCompatActivity() {

     private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private fun onBack() {
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
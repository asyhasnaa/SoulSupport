package com.dicoding.soulsupport.ui.note.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.soulsupport.databinding.ActivityAddNoteBinding


class AddUpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
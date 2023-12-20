package com.dicoding.soulsupport.ui.note.add

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.soulsupport.data.database.Note
import com.dicoding.soulsupport.data.database.NoteDatabaseHelper
import com.dicoding.soulsupport.databinding.ActivityAddNoteBinding
import com.dicoding.soulsupport.utils.DateFormatter


class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        binding.saveButton.setOnClickListener {
            val title = binding.addTitle.text.toString()
            val desc = binding.addDesc.text.toString()
            val date = DateFormatter.dateFormat()
            val note = Note(0, title, desc, date)
            db.insertNote(note)
            finish()
            Toast.makeText(this, "Catatanmu sudah tersimpan", Toast.LENGTH_SHORT).show()
        }
        onBack()
    }
    private fun onBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

}
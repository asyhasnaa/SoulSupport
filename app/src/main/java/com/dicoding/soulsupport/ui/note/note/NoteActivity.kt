package com.dicoding.soulsupport.ui.note.note

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.soulsupport.data.database.NoteDatabaseHelper
import com.dicoding.soulsupport.databinding.ActivityNoteBinding
import com.dicoding.soulsupport.ui.adapter.NoteAdapter
import com.dicoding.soulsupport.ui.note.add.AddNoteActivity

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private lateinit var db: NoteDatabaseHelper
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        noteAdapter = NoteAdapter(db.getAllNotes(), this)

        binding.itemNote.layoutManager = LinearLayoutManager(this)
        binding.itemNote.adapter = noteAdapter

        binding.extendedFab.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

        onBack()
    }

    private fun onBack() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.updateData(db.getAllNotes())
    }
}

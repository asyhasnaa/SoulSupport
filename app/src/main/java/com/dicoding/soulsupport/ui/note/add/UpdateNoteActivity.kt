package com.dicoding.soulsupport.ui.note.add

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.soulsupport.data.database.Note
import com.dicoding.soulsupport.data.database.NoteDatabaseHelper
import com.dicoding.soulsupport.databinding.ActivityUpdateNoteBinding
import com.dicoding.soulsupport.utils.DateFormatter

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if (noteId == -1){
            finish()
            return
        }
        val note = db.getNoteByID(noteId)
        binding.editTitle.setText(note.title)
        binding.editDesc.setText(note.desc)

        binding.saveEdit.setOnClickListener {
            val newTitle = binding.editTitle.text.toString()
            val newDesc = binding.editDesc.text.toString()
            val newDate = DateFormatter.dateFormat()
            val updatedNote = Note(noteId, newTitle,newDesc, newDate)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Catatanmu berhasil diperbarui", Toast.LENGTH_SHORT).show()
        }
        onBack()
    }
    private fun onBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
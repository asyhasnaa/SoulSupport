package com.dicoding.soulsupport.ui.note.detail

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.soulsupport.data.database.NoteDatabaseHelper
import com.dicoding.soulsupport.databinding.ActivityDetailNoteBinding
import com.dicoding.soulsupport.ui.note.add.UpdateNoteActivity

class DetailNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailNoteBinding
    private val db: NoteDatabaseHelper = NoteDatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteId = intent.getIntExtra("note_id", -1)
        val noteTitle = intent.getStringExtra("note_title")
        val noteDate = intent.getStringExtra("note_date")
        val noteDesc = intent.getStringExtra("note_desc")

        binding.noteDetailTitle.text = noteTitle
        binding.tvItemDate.text = noteDate
        binding.noteDetailBody.text = noteDesc

        binding.ivEdit.setOnClickListener {
            val intent = Intent(this, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", noteId)
            }
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            showDeleteConfirmationDialog(noteId)
        }
        onBack()

    }

    private fun showDeleteConfirmationDialog(noteId: Int) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
        alertDialogBuilder.setPositiveButton("Ya") { _: DialogInterface, _: Int ->
            db.deleteNote(noteId)
            Toast.makeText(this, "Catatan berhasil dihapus", Toast.LENGTH_SHORT).show()

            finish()
        }
        alertDialogBuilder.setNegativeButton("Tidak", null)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun onBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}

package com.dicoding.soulsupport.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.data.database.Note
import com.dicoding.soulsupport.data.database.NoteDatabaseHelper
import com.dicoding.soulsupport.ui.note.add.UpdateNoteActivity
import com.dicoding.soulsupport.ui.note.detail.DetailNoteActivity

class NoteAdapter(private var notes: List<Note>, context: Context) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val db: NoteDatabaseHelper = NoteDatabaseHelper(context)
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        val descTextView: TextView = itemView.findViewById(R.id.tv_desc)
        val dateTextView: TextView = itemView.findViewById(R.id.tv_item_date)
        val updateButton: ImageView = itemView.findViewById(R.id.iv_edit)
        val deleteButton: ImageView = itemView.findViewById(R.id.iv_delete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }
    override fun getItemCount(): Int = notes.size
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.descTextView.text = note.desc
        holder.dateTextView.text = note.date
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            updateData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Catatan berhasil dihapus", Toast.LENGTH_SHORT)
                .show()
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
                putExtra("note_title", note.title)
                putExtra("note_date", note.date)
                putExtra("note_desc", note.desc)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    fun updateData(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}
package com.dicoding.soulsupport.ui.note.note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.soulsupport.data.database.NoteDatabaseHelper
import com.dicoding.soulsupport.databinding.FragmentNoteBinding
import com.dicoding.soulsupport.ui.adapter.NoteAdapter
import com.dicoding.soulsupport.ui.note.add.AddNoteActivity

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: NoteDatabaseHelper
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = NoteDatabaseHelper(requireContext())
        noteAdapter = NoteAdapter(db.getAllNotes(), requireContext())

        binding.itemNote.layoutManager = LinearLayoutManager(requireContext())
        binding.itemNote.adapter = noteAdapter

        binding.extendedFab.setOnClickListener {
            startActivity(Intent(requireContext(), AddNoteActivity::class.java))
        }

        onBack()
    }

    private fun onBack() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onResume() {
        super.onResume()
        noteAdapter.updateData(db.getAllNotes())
    }
}
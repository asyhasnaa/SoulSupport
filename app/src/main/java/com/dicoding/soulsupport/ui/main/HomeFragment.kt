package com.dicoding.soulsupport.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.databinding.FragmentHomeBinding
import com.dicoding.soulsupport.ui.article.ArticleActivity
import com.dicoding.soulsupport.ui.chat.ChatActivity
import com.dicoding.soulsupport.ui.meditation.MeditationActivity
import com.dicoding.soulsupport.ui.note.note.NoteFragment
import com.dicoding.soulsupport.ui.profile.ProfileFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.ivProfile.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, ProfileFragment()).commit()
        }

        binding.Menu1.setOnClickListener {
            startActivity(Intent(requireContext(), ChatActivity::class.java))
        }

        binding.Menu2.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, NoteFragment()).commit()
        }

        binding.Menu3.setOnClickListener {
            startActivity(Intent(requireContext(), MeditationActivity::class.java))
        }

        binding.Menu4.setOnClickListener {
            startActivity(Intent(requireContext(), ArticleActivity::class.java))
        }
    }
}



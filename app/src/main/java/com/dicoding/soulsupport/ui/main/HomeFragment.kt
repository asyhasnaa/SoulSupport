package com.dicoding.soulsupport.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.databinding.FragmentHomeBinding
import com.dicoding.soulsupport.ui.article.ArticleActivity
import com.dicoding.soulsupport.ui.chat.ChatActivity
import com.dicoding.soulsupport.ui.meditation.MeditationFragment
import com.dicoding.soulsupport.ui.note.note.NoteActivity
import com.dicoding.soulsupport.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        onBackPressed()
    }

    private fun setupClickListeners() {
        binding.ivProfile.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, ProfileFragment()).addToBackStack(null).commit()
            val bottomNavigationView =
                requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigationView.menu.findItem(R.id.bottom_profile)?.isChecked = true
        }

        binding.Menu1.setOnClickListener {
            startActivity(Intent(requireContext(), ChatActivity::class.java))
        }

        binding.Menu2.setOnClickListener {
            startActivity(Intent(requireContext(), NoteActivity::class.java))
        }

        binding.Menu3.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, MeditationFragment()).addToBackStack(null).commit()

            val bottomNavigationView =
                requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigationView.menu.findItem(R.id.bottom_meditate)?.isChecked = true
        }

        binding.Menu4.setOnClickListener {
            startActivity(Intent(requireContext(), ArticleActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            activity?.finish()
        }
    }

}



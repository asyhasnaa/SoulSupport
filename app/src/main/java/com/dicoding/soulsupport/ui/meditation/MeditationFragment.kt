package com.dicoding.soulsupport.ui.meditation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.soulsupport.databinding.FragmentMeditationBinding

class MeditationFragment : Fragment() {
    private var _binding: FragmentMeditationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeditationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()

        onBack()
    }

    private fun setupClickListeners() {
        binding.cardView1.setOnClickListener {
            startActivity(Intent(requireContext(), MeditationActivity::class.java))
        }
        binding.cardView2.setOnClickListener {
            startActivity(Intent(requireContext(), Meditation2Activity::class.java))
        }
        binding.cardView3.setOnClickListener {
            startActivity(Intent(requireContext(), MeditationActivity::class.java))
        }
    }

    @Suppress("DEPRECATION")
    private fun onBack() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}
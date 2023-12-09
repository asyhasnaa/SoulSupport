package com.dicoding.soulsupport.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.soulsupport.databinding.FragmentProfileBinding
import com.dicoding.soulsupport.ui.camera.CameraActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private var imageUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imgProfile.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        imageUrl = requireActivity().intent.getStringExtra("EXTRA_IMAGE_URI")

        // Tampilkan gambar
        if (imageUrl != null) {
            val imageUri = Uri.parse(imageUrl)
            binding.imgProfile.setImageURI(imageUri)
        }
        onBack()
    }

    private fun onBack() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}
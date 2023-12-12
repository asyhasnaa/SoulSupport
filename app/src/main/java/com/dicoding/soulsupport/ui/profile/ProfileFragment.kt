package com.dicoding.soulsupport.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.dicoding.soulsupport.databinding.FragmentProfileBinding
import com.dicoding.soulsupport.ui.camera.CameraActivity
import com.dicoding.soulsupport.ui.camera.CameraViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val cameraViewModel: CameraViewModel by activityViewModels()

    private val CAMERA_REQUEST_CODE = 123

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgProfile.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }

        if (!cameraViewModel.imageUri.isNullOrEmpty()) {
            val imageUri = Uri.parse(cameraViewModel.imageUri)

            Glide.with(requireContext())
                .load(imageUri)
                .into(binding.imgProfile)
        }

        onBack()
    }

    private fun onBack() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if (!cameraViewModel.imageUri.isNullOrEmpty()) {
                val imageUri = Uri.parse(cameraViewModel.imageUri)

                Glide.with(requireContext())
                    .load(imageUri)
                    .into(binding.imgProfile)
            }
        }
    }
}

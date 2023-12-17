package com.dicoding.soulsupport.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dicoding.soulsupport.databinding.FragmentProfileBinding
import com.dicoding.soulsupport.ui.ViewModelFactory
import com.dicoding.soulsupport.ui.camera.CameraActivity
import com.dicoding.soulsupport.ui.camera.CameraViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val cameraViewModel: CameraViewModel by activityViewModels()
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val darkViewModel by viewModels<DarkModeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }


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

        if (!cameraViewModel.imageUri.isNullOrEmpty()) {
            val imageUri = Uri.parse(cameraViewModel.imageUri)

            Glide.with(requireContext())
                .load(imageUri)
                .into(binding.imgProfile)
        }

        switchTheme()
        actionCamera()
        logout()
        onBack()

    }

    private fun switchTheme() {
        darkViewModel.getThemeSettings().observe(viewLifecycleOwner) { isDarkModeActive: Boolean ->
            binding.swithMode.isChecked = isDarkModeActive
            updateAppTheme(isDarkModeActive)
        }
        binding.swithMode.setOnCheckedChangeListener { _, isChecked ->
            darkViewModel.saveThemeSetting(isChecked)
            updateAppTheme(isChecked)
        }
    }

    private fun updateAppTheme(isDarkModeActive: Boolean) {
        if (isDarkModeActive) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


    private fun actionCamera() {
        binding.imgProfile.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }

    private fun logout(){
        binding.logout.setOnClickListener {

            AlertDialog.Builder(requireContext()).apply {
                setTitle("Keluar?")
                setMessage("Apa Kamu yakin ingin keluar")
                setPositiveButton("Ya") { _, _ ->
                    viewModel.logout()
                }
                setNegativeButton("Tidak") { _, _ ->
                    // Handle the case where the user chooses not to exit
                }
                create()
                show()
            }

        }
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

package com.dicoding.soulsupport.ui.profile

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.databinding.ActivityProfileBinding
import com.dicoding.soulsupport.ui.camera.CameraActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private var imageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgProfile.setOnClickListener{
            val intent = Intent(this,CameraActivity::class.java)
            startActivity(intent)
        }

        imageUrl = intent.getStringExtra("EXTRA_IMAGE_URI")

        // Tampilkan gambar
        if (imageUrl != null) {
            val imageUri = Uri.parse(imageUrl)
            binding.imgProfile.setImageURI(imageUri)
        }
    }

}
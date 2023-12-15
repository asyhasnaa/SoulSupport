package com.dicoding.soulsupport.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.databinding.ActivityMainBinding
import com.dicoding.soulsupport.ui.chat.ChatFragment
import com.dicoding.soulsupport.ui.meditation.MeditationFragment
import com.dicoding.soulsupport.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigation()
        replaceFragment(HomeFragment())
    }

    private fun bottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                }

                R.id.bottom_meditate -> {
                    replaceFragment(MeditationFragment())
                }

                R.id.bottom_chat -> {
                    replaceFragment(ChatFragment())
                }

                R.id.bottom_profile -> {
                    replaceFragment(ProfileFragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        if (currentFragment is ChatFragment ||currentFragment is MeditationFragment || currentFragment is ProfileFragment) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            super.onBackPressed()
        }
    }
}

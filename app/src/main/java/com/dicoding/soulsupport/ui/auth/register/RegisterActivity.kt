package com.dicoding.soulsupport.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.dicoding.soulsupport.data.Result
import com.dicoding.soulsupport.data.remote.refrofit.ApiService
import com.dicoding.soulsupport.data.repository.AuthRepository
import com.dicoding.soulsupport.databinding.ActivityRegisterBinding
import com.dicoding.soulsupport.ui.ViewModelFactory
import com.dicoding.soulsupport.ui.auth.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        registerAction()
        onBack()
    }

    private fun registerAction() {
        binding.btnRsDaftar.setOnClickListener {
            val name = binding.edRegisterUsername.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.text.toString()
            val confPassword = binding.edRegisterConfPassword.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confPassword.isNotEmpty()) {
                if (confPassword == password) {
                    viewModel.registerUser(name, email, password, confPassword)
                        .observe(this) { result ->
                            when (result) {
                                is Result.Loading -> {
                                    binding.progressBar.visibility = View.VISIBLE
                                }

                                is Result.Success -> {
                                    binding.progressBar.visibility = View.GONE
                                    AlertDialog.Builder(this).apply {
                                        setTitle(result.data.msg)
                                        setMessage("akun dengan email $email terdaftar")
                                        setPositiveButton("Lanjut") { _, _ ->
                                            finish()
                                        }
                                        create()
                                        show()
                                    }
                                }
                                is Result.Error -> {
                                    binding.progressBar.visibility = View.GONE
                                    Toast.makeText(this, "Akun sudah terdaftar", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                } else {
                    Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            }else {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onBack() {
        binding.linkLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
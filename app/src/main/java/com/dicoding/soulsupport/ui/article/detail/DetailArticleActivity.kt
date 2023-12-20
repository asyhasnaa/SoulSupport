package com.dicoding.soulsupport.ui.article.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.data.database.GenerateDummyArticles
import com.dicoding.soulsupport.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val articleId = intent.getIntExtra("id",-1)
        val dummyArticle = GenerateDummyArticles.generateDummyArticle().find { it.id == articleId }

        if (dummyArticle != null) {
            binding.ivDetailArticle.setImageResource(dummyArticle.image)
            binding.tvDetailTitle.text = dummyArticle.title
            binding.tvDetailArticle.text = dummyArticle.content
        } else {
            Toast.makeText(this, "Article Gagal memuat", Toast.LENGTH_SHORT).show()
        }
    }
}
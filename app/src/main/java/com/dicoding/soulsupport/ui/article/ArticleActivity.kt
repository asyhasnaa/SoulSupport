package com.dicoding.soulsupport.ui.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.soulsupport.data.database.GenerateDummyArticles
import com.dicoding.soulsupport.databinding.ActivityArticleBinding
import com.dicoding.soulsupport.ui.adapter.ArticleAdapter

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    private lateinit var adapter: ArticleAdapter
    private val dummyArticle = GenerateDummyArticles.generateDummyArticle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArticleAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding.itemArticle.layoutManager = layoutManager
        binding.itemArticle.adapter = adapter

        adapter.submitList(dummyArticle)


        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                Toast.makeText(this@ArticleActivity, " berhasil", Toast.LENGTH_SHORT).show()
                false
            }
        }

        onBack()
    }

    private fun onBack() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}
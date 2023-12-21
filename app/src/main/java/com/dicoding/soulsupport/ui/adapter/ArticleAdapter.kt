package com.dicoding.soulsupport.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.soulsupport.data.database.Article
import com.dicoding.soulsupport.databinding.ItemArticleBinding
import com.dicoding.soulsupport.ui.article.detail.DetailArticleActivity

class ArticleAdapter: ListAdapter<Article, ArticleAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MyViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: Article) {
            binding.tvTitleArticle.text = items.title
            binding.tvDescArticle.text = items.content
            Glide.with(binding.root.context)
                .load(items.image)
                .into(binding.ivArticle)
                .clearOnDetach()
            itemView.setOnClickListener {
                val intent = Intent(binding.cardNote.context, DetailArticleActivity::class.java)
                intent.putExtra("id",items.id)
                itemView.context.startActivity(intent)
            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}
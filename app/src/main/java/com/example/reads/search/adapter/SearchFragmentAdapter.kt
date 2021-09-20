package com.example.reads.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reads.R
import com.example.reads.data.model.Book

class SearchFragmentAdapter(private val context: Fragment, val data: Book) :
    RecyclerView.Adapter<SearchFragmentAdapter.SearchFragmentViewHolder>() {
    class SearchFragmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookCover: ImageView = view.findViewById(R.id.book_image)
        val bookTitle: TextView = view.findViewById(R.id.book_title)
        val bookDescription: TextView = view.findViewById(R.id.book_description)
    }

    override fun getItemCount(): Int {
        return data.items?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFragmentViewHolder {
        return SearchFragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchFragmentViewHolder, position: Int) {
        val item = data.items?.get(position)
        try {
            Glide.with(context).load(
                item?.volumeInfo?.imageLinks?.thumbnail?.toUri()?.buildUpon()?.scheme("https")?.build()
            ).into(holder.bookCover)
        } catch (e: Exception) {
            holder.bookCover.setImageResource(R.drawable.ic_broken_image)
        }
        holder.bookTitle.text = item?.volumeInfo?.title
        holder.bookDescription.text = item?.volumeInfo?.description

    }
}
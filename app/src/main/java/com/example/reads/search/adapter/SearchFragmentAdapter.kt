package com.example.reads.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.reads.R
import com.example.reads.data.remote.model.Book

class SearchFragmentAdapter(val data: Book) :
    RecyclerView.Adapter<SearchFragmentAdapter.SearchFragmentViewHolder>() {
    class SearchFragmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookCover: ImageView = view.findViewById(R.id.book_image)
        val bookTitle: TextView = view.findViewById(R.id.book_title)
        val bookDescription: TextView = view.findViewById(R.id.book_description)
    }

    override fun getItemCount(): Int {
        return data.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFragmentViewHolder {
        return SearchFragmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchFragmentViewHolder, position: Int) {
        val item = data.items[position]
        try {
            holder.bookCover.load(
                item.volumeInfo.imageLinks.thumbnail.toUri().buildUpon().scheme("https").build()
            ) {
                placeholder(R.drawable.ic_broken_image)
                transformations(RoundedCornersTransformation(3f))
            }
        } catch (e: Exception) {
            holder.bookCover.setImageResource(R.drawable.ic_broken_image)
        }
        holder.bookTitle.text = item.volumeInfo.title
        holder.bookDescription.text = item.volumeInfo.description

    }
}
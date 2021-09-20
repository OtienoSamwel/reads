package com.example.reads.home.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reads.data.model.Book
import com.example.reads.databinding.HomeFragmentItenBinding

class HomeFragmentAdapter(private val data: Book) :
    RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder>() {

    inner class HomeFragmentViewHolder(val binding: HomeFragmentItenBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder {
        val binding =
            HomeFragmentItenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
        val data = data.items?.get(position)
        with(holder.binding) {
            Glide.with(root.context).load(
                data?.volumeInfo?.imageLinks?.thumbnail?.toUri()?.buildUpon()?.scheme("https")
                    ?.build()
            ).error(ColorDrawable(Color.RED))
                .placeholder(ColorDrawable(Color.GRAY))
                .centerCrop()
                .into(bookImage)
        }
    }

    override fun getItemCount(): Int {
        return data.items?.size!!
    }
}
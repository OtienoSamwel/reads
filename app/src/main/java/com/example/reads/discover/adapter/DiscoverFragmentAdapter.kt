package com.example.reads.discover.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.reads.discover.GenreFragment

class DiscoverFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    companion object {
        val myFragmentLabels: List<String> = listOf(
            "Fiction",
            "Programming",
            "Fantasy",
            "Mystery",
            "Thriller",
            "Novel",
            "NonFiction",
            "Romance",
            "Memoir",
            "Autobiography",
            "Poetry",
            "Psychology",
            "Drama"
        )
        var positioning: Int = 0
    }

    override fun getItemCount(): Int {
        return myFragmentLabels.size
    }

    override fun createFragment(position: Int): Fragment {
        positioning = position
        return GenreFragment()
    }

}
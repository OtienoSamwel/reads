package com.example.reads.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.reads.databinding.FragmentMainDiscoverBinding
import com.example.reads.discover.adapter.DiscoverFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainDiscoverFragment : Fragment() {
    private var _binding: FragmentMainDiscoverBinding? = null
    private val binding: FragmentMainDiscoverBinding get() = _binding!!
    private lateinit var viewPager: ViewPager2
    private lateinit var discoverFragmentAdapter: DiscoverFragmentAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discoverFragmentAdapter = DiscoverFragmentAdapter(this)
        viewPager = binding.discoverViewPager
        viewPager.adapter = discoverFragmentAdapter

        TabLayoutMediator(binding.discoverTabLayout, viewPager) { tab, position ->
            tab.text = DiscoverFragmentAdapter.myFragmentLabels[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.example.reads.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.reads.databinding.FragmentGenreBinding
import com.example.reads.discover.adapter.DiscoverFragmentAdapter
import com.example.reads.discover.viewmodel.DiscoverViewModel
import com.example.reads.home.adapters.HomeFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : Fragment() {
    private var _binding: FragmentGenreBinding? = null
    private val binding: FragmentGenreBinding get() = _binding!!
    private val viewModel: DiscoverViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val genre: String =
            DiscoverFragmentAdapter.myFragmentLabels[DiscoverFragmentAdapter.positioning].lowercase()
        binding.genreRecyclerView.layoutManager = GridLayoutManager(activity, 4)


        val adapter = HomeFragmentAdapter()
        binding.genreRecyclerView.adapter = adapter
        viewModel.getGenreBooks("subject:$genre").observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
//        viewModel.data.observe(viewLifecycleOwner) {
//            binding.genreRecyclerView.adapter = HomeFragmentAdapter()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
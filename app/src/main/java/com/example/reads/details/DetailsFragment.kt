package com.example.reads.details

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.reads.data.model.Item
import com.example.reads.databinding.FragmentDetailsBinding
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailsFragmentArgs by navArgs()
        val moshi: Moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Item::class.java)
        val data = adapter.fromJson(args.data)

        val imageLink =
            data?.volumeInfo?.imageLinks?.thumbnail?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(requireActivity()).load(imageLink)
            .error(ColorDrawable(Color.RED))
            .placeholder(ColorDrawable(Color.GRAY))
            .centerCrop()
            .into(binding.bookCoverImageView)

        with(binding) {
            bookTitleTextView.text = "Title : ${data?.volumeInfo?.title}"
            bookAuthorTextView.text = "Author : ${data?.volumeInfo?.authors?.get(0)}"
            ratingTextView.text = "Rating : ${data?.volumeInfo?.averageRating}"
            bookDescriptionTextView.text = "${data?.volumeInfo?.description}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
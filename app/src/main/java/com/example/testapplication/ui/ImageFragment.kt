package com.example.testapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.testapplication.R
import com.example.testapplication.ui.view.PinchZoomImageView

class ImageFragment : Fragment(R.layout.fragment_image) {

    private val args by navArgs<ImageFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pinchZoomView = view.findViewById<PinchZoomImageView>(R.id.pinchZoomView)
        val imageView = pinchZoomView.findViewById<ImageView>(R.id.imageView)
        Glide.with(requireContext())
            .load(args.imageUrl)
            .fitCenter()
            .into(imageView)

    }
}
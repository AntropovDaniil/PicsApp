package com.example.picsapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.picsapp.databinding.FragmentPictureBinding
import com.example.picsapp.viewmodel.PictureViewModel

class PictureFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<PictureFragmentArgs>()

    private val viewModel: PictureViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        setData()
        setListener()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setData(){
        binding.imageView.load(args.largeImage.largeImageUrl)
    }

    @SuppressLint("ResourceType")
    private fun setListener(){
        binding.setWallpaperButton.setOnClickListener {
            viewModel.setWallpaper(args.largeImage.largeImageUrl)
        }
    }
}
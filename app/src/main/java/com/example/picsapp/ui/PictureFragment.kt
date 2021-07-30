package com.example.picsapp.ui

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.clear
import coil.load
import coil.request.ImageRequest
import com.example.picsapp.databinding.FragmentPictureBinding
import com.example.picsapp.viewmodel.PictureViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            viewModel.setWallpaper(args.largeImage)
            Toast.makeText(requireContext(), "Wallpaper updated", Toast.LENGTH_SHORT).show()
        }
    }
}
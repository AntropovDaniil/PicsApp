package com.example.picsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.picsapp.ui.PictureListFragmentDirections
import com.example.picsapp.databinding.ImageItemBinding
import com.example.picsapp.model.LargeImageModel
import com.example.picsapp.model.PixabayPicture

class PictureListAdapter: RecyclerView.Adapter<PictureListAdapter.PictureListViewHolder>(){

    private var _rvBinding: ImageItemBinding? = null
    private val rvBinding get() = _rvBinding!!

    private var imageList: List<PixabayPicture> = mutableListOf()

    inner class PictureListViewHolder(val binding: ImageItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        _rvBinding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PictureListViewHolder(rvBinding)
    }

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        holder.binding.imageView.load(imageList[position].webformatUrl)

        holder.binding.imageView.setOnClickListener {
            val imageModel = getImageModel(position)
            val action = PictureListFragmentDirections.actionPictureListFragmentToPictureFragment(imageModel)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<PixabayPicture>){
        imageList = list
        notifyDataSetChanged()
    }

    private fun getImageModel(position: Int): LargeImageModel{
        return LargeImageModel(
            largeImageUrl = imageList[position].largeImageUrl,
            imageWidth = imageList[position].imageWidth,
            imageHeight = imageList[position].imageHeight
        )
    }
}
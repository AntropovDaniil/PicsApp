package com.example.picsapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.picsapp.ui.PictureListFragmentDirections
import com.example.picsapp.model.LargeImageModel
import com.example.picsapp.model.PixabayPicture
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.picsapp.R
import com.example.picsapp.databinding.PictureItemBinding

class PictureListAdapter(context: Context): PagingDataAdapter<PixabayPicture, PictureListViewHolder>(PictureDiffItemCallback)  {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        return PictureListViewHolder(layoutInflater.inflate(R.layout.picture_item, parent, false))
    }

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PictureListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val viewBinding by viewBinding(PictureItemBinding::bind)

    fun bind(picture: PixabayPicture?){
        with(viewBinding){
            imageView.load(picture?.webformatUrl){
                placeholder(ColorDrawable(Color.TRANSPARENT))
            }

            itemView.setOnClickListener {
                if (picture != null) {
                    val imageModel = LargeImageModel(
                        largeImageUrl = picture.largeImageUrl,
                        imageWidth = picture.imageWidth,
                        imageHeight = picture.imageHeight
                    )
                    val action = PictureListFragmentDirections.actionPictureListFragmentToPictureFragment(imageModel)
                    it.findNavController().navigate(action)
                }
            }
        }
    }
}

private object PictureDiffItemCallback : DiffUtil.ItemCallback<PixabayPicture>() {

    override fun areItemsTheSame(oldItem: PixabayPicture, newItem: PixabayPicture): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PixabayPicture, newItem: PixabayPicture): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.largeImageUrl == newItem.largeImageUrl &&
                oldItem.previewUrl == newItem.previewUrl &&
                oldItem.type == newItem.type
    }
}
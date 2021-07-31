package com.example.picsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.picsapp.ui.CategoryListFragmentDirections
import com.example.picsapp.databinding.CategoryItemBinding
import com.example.picsapp.model.Category

class CategoryListAdapter: RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>() {

    private var _rvBiding: CategoryItemBinding? = null
    private val rvBinding get() = _rvBiding!!

    private val categoryList = mutableListOf<Category>()

    init {
        initCategoryList()
    }

    inner class CategoryListViewHolder(val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        _rvBiding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryListViewHolder(rvBinding)
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        val categoryName = categoryList[position].categoryName
        val categoryIcon = categoryList[position].iconResource

        holder.binding.categoryName.text = categoryName
        holder.binding.categoryIcon.setImageResource(categoryIcon)

        holder.binding.categoryId.setOnClickListener {
            val action = CategoryListFragmentDirections.actionCategoryListFragmentToPictureListFragment(categoryName.toLowerCase())
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    private fun initCategoryList(){
        for (category in Category.values()){
            categoryList.add(category)
        }
    }

}
package com.example.picsapp.ui

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picsapp.adapter.CategoryListAdapter
import com.example.picsapp.consts.Constants.Companion.BASE_URL
import com.example.picsapp.databinding.FragmentCategoryListBinding

class CategoryListFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CategoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryListBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        initRecyclerView()
        initListener()

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView(){
        adapter = CategoryListAdapter()

        binding.categoryRv.layoutManager = LinearLayoutManager(requireContext())
        binding.categoryRv.adapter = adapter
    }

    private fun initListener(){
        binding.pixabayLogo.setOnClickListener {
            val websiteIntent = Intent()
            websiteIntent.apply {
                setAction(Intent.ACTION_VIEW)
                addCategory(Intent.CATEGORY_BROWSABLE)
                setData(BASE_URL.toUri())
            }
            startActivity(websiteIntent)
        }
    }

}
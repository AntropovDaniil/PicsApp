package com.example.picsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.picsapp.adapter.PictureListAdapter
import com.example.picsapp.databinding.FragmentPictureListBinding
import com.example.picsapp.viewmodel.PictureListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PictureListFragment : Fragment() {

    private var _binding: FragmentPictureListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PictureListViewModel by viewModels()//= ViewModelProvider(requireActivity()).get(PictureListViewModel::class.java)

    //private val viewModel: PictureListViewModel by viewModels {viewModeProvider.get()}

    private val args by navArgs<PictureListFragmentArgs>()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        PictureListAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureListBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        initRecyclerView()
        setData()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView(){
        binding.pictureRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.pictureRv.adapter = adapter
    }

    private fun setData(){
//        //todo delete???
//        viewModel.pictureList.clear()
//        viewModel.getPicturesFromCategory(args.categoryName)
//        viewModel.imageList.observe(requireActivity()){
//            adapter.setData(it)
//        }

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getPictureList(args.categoryName).collectLatest(
                adapter::submitData
            )
        }

    }

}
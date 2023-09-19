package com.bondstein.onlineattendanceapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bondstein.onlineattendanceapp.databinding.FragmentHomeBinding
import com.bondstein.onlineattendanceapp.ui.RecyclerViewAdapter
import com.bondstein.onlineattendanceapp.utils.State
import com.bondstein.onlineattendanceapp.utils.Utility
import com.bondstein.onlineattendanceapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var pageNumber = 1
    private var totalPages = 0
    private var isLoading = false
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val recyclerViewAdapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getBaseData(pageNumber++)
        mainViewModel.baseData.observe(this) {
            when (it) {
                is State.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                }

                is State.Success -> {
                    isLoading = false
                    binding.loader.visibility = View.GONE
                    totalPages = it.data.meta.total
                    recyclerViewAdapter.addItems(it.data.data)
                }

                is State.Error -> {
                    binding.topbar.visibility = View.GONE
                    binding.loader.visibility = View.GONE
                    if (!Utility.hasInternet(requireContext())) {
                        Utility.showNoInternetDialog(requireContext(), requireActivity())
                    }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerview.adapter = recyclerViewAdapter
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && !isLoading && pageNumber <= totalPages) {
                    isLoading = true
                    mainViewModel.getBaseData(pageNumber++)
                }
            }
        })
        return binding.root
    }

}
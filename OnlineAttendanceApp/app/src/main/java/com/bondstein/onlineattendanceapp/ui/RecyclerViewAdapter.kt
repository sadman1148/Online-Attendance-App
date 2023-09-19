package com.bondstein.onlineattendanceapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bondstein.onlineattendanceapp.R
import com.bondstein.onlineattendanceapp.data.remote.model.Data
import com.bondstein.onlineattendanceapp.databinding.RecyclerItemBinding

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val dataList = mutableListOf<Data>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(dataItem: List<Data>) {
        dataList.addAll(dataItem)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.storeName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("store_id", dataList[position].id)
            bundle.putString("store_name", dataList[position].name)
            bundle.putString("store_address", dataList[position].address)
            it.findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }

    override fun getItemCount() = dataList.size

}
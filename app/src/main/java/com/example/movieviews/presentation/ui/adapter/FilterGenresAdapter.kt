package com.example.movieviews.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviews.R
import com.example.movieviews.data.models.Genre
import com.example.movieviews.databinding.ItemViewSelectFilterBinding

class FilterGenresAdapter(val list: MutableList<Genre>) :
    RecyclerView.Adapter<FilterGenresAdapter.MyViewHolder>() {

    var listener: AdapterClickListener<Genre>? = null

    fun setData(list: List<Genre>?) {
        this.list.clear()
        if (!list.isNullOrEmpty()) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun selectPosition(position: Int, data: Genre) {
        notifyItemChanged(position, data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_view_select_filter, parent, false
            )
        )
    }

    fun getSelectValue(): ArrayList<Genre> {
        val selectList = arrayListOf<Genre>()
        list.forEach {
            if (it.isChecked) selectList.add(it)
        }
        return selectList
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(list[position], position)
    }

    override fun getItemCount(): Int  = list.size

    inner class MyViewHolder(val binding: ItemViewSelectFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(genre: Genre, position: Int) {
            binding.name = genre.name
            binding.isChecked = genre.isChecked
            itemView.setOnClickListener {
                genre.isChecked = !genre.isChecked
                selectPosition(position, genre)
                listener?.onItemClickCallback(data = genre)
            }
        }
    }
}
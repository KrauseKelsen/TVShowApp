package com.example.tvshowapp3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tvshowapp3.databinding.TvShowLayoutAdapterBinding
import com.example.tvshowapp3.models.TvShowItem

class TVShowAdapter(private val iOnClickListener: IOnClickListener) : RecyclerView.Adapter<TVShowAdapter.MyViewHolder>() {

    interface IOnClickListener{
        fun onClickListenerTVShowItem(tvShowItem: TvShowItem)
    }

    inner class MyViewHolder(val binding: TvShowLayoutAdapterBinding) : RecyclerView.ViewHolder(binding.root){
        fun blindeoVista(tvShowItem: TvShowItem){
            binding.cardView.setOnClickListener { iOnClickListener.onClickListenerTVShowItem(tvShowItem) }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<TvShowItem>() {
        override fun areItemsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var tVShows: List<TvShowItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            TvShowLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTvShow = tVShows[position]

        holder.binding.apply {
            textView.text = currentTvShow.name

            imageView.load(currentTvShow.image.original) {
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.blindeoVista(currentTvShow)
    }

    override fun getItemCount() = tVShows.size

}
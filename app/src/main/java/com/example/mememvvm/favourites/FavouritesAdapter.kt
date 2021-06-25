package com.example.mememvvm.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mememvvm.database.MemesEntity
import com.example.mememvvm.databinding.MemesListItemBinding


class FavouritesAdapter() :
    ListAdapter<MemesEntity, FavouritesAdapter.FavouritesViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(
            MemesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val memes = getItem(position)
        holder.bind(memes)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<MemesEntity>() {
        override fun areItemsTheSame(oldItem: MemesEntity, newItem: MemesEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MemesEntity, newItem: MemesEntity): Boolean {
            return oldItem.url == newItem.url
        }

    }

    class FavouritesViewHolder(private var binding: MemesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memes: MemesEntity) {
            //binding.recyclerViewLikeTextView.text = memes.ups.toString()
            //Glide.with()
            binding.memes = memes
            binding.executePendingBindings()
        }
    }
}
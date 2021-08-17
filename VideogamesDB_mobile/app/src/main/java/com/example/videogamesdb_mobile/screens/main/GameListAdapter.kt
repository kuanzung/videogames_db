package com.example.videogamesdb_mobile.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.videogamesdb_mobile.R
import com.example.videogamesdb_mobile.models.Game
import com.squareup.picasso.Picasso

class GameListAdapter : ListAdapter<Game, GameListAdapter.ViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.image_url, current.name)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewBanner: ImageView = itemView.findViewById(R.id.imageViewBanner)
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)

        fun bind(imageUrl: String?, name: String) {
            Picasso.get().load(imageUrl).into(imageViewBanner)
            textViewName.text = name
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout, parent, false)
                return ViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}
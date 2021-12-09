package com.example.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paging.network.CharacterData

class RecyclerViewAdapter: PagingDataAdapter<CharacterData, RecyclerViewAdapter
                                                            .MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent,
                                                                    false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val imageThumb: ImageView = view.findViewById(R.id.imageView)
        private val name: TextView = view.findViewById(R.id.text_name)
        private val description: TextView = view.findViewById(R.id.text_description)

        fun bind(data: CharacterData){

            name.text = data.name
            description.text = data.species
            Glide.with(imageThumb).load(data.image).circleCrop().into(imageThumb)

        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterData>(){

        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {

            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {

            return oldItem.name ==  newItem.name && oldItem.species == newItem.species
        }


    }
}
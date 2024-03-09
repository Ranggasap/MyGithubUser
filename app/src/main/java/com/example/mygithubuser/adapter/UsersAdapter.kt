package com.example.mygithubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.data.response.ItemsItem
import com.example.mygithubuser.data.response.UserFollowingGithubResponseItem
import com.example.mygithubuser.databinding.ItemUserBinding

class UsersAdapter: ListAdapter<ItemsItem, UsersAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val users = getItem(position)
        holder.bind(users)
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(users)
        }
    }

    class MyViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){
         fun bind(users: ItemsItem){
            binding.tvItem.text = "${users.login}"
            Glide.with(binding.root.context)
                .load(users.avatarUrl)
                .into(binding.ciItem)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>(){
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: ItemsItem)
    }
}
package com.example.mygithubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.data.response.UserFollowersGithubResponseItem
import com.example.mygithubuser.databinding.ItemUserBinding

class UserFollowersAdapter: ListAdapter<UserFollowersGithubResponseItem, UserFollowersAdapter.MyViewHolder>(
    DIFF_CALLBACK
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val users = getItem(position)
        holder.bind(users)
    }

    class MyViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(users: UserFollowersGithubResponseItem){
            binding.tvItem.text = "${users.login}"
            Glide.with(binding.root.context)
                .load(users.avatarUrl)
                .into(binding.ciItem)
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserFollowersGithubResponseItem>(){
            override fun areItemsTheSame(
                oldItem: UserFollowersGithubResponseItem,
                newItem: UserFollowersGithubResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserFollowersGithubResponseItem,
                newItem: UserFollowersGithubResponseItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
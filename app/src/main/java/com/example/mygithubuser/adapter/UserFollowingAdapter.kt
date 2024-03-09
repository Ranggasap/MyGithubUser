package com.example.mygithubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.data.response.UserFollowingGithubResponseItem
import com.example.mygithubuser.databinding.ItemUserBinding

class UserFollowingAdapter: ListAdapter<UserFollowingGithubResponseItem, UserFollowingAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val users = getItem(position)
        holder.bind(users)
    }

    class MyViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(users: UserFollowingGithubResponseItem){
            binding.tvItem.text = "${users.login}"
            Glide.with(binding.root.context)
                .load(users.avatarUrl)
                .into(binding.ciItem)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserFollowingGithubResponseItem>(){
            override fun areItemsTheSame(
                oldItem: UserFollowingGithubResponseItem,
                newItem: UserFollowingGithubResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserFollowingGithubResponseItem,
                newItem: UserFollowingGithubResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
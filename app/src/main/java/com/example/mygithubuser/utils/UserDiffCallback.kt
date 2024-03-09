package com.example.mygithubuser.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.mygithubuser.database.UserEntity

class UserDiffCallback(private val oldUserList: List<UserEntity>, private val newUserList: List<UserEntity>): DiffUtil.Callback()
{
    override fun getOldListSize(): Int {
        return oldUserList.size
    }

    override fun getNewListSize(): Int {
        return newUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUserList[oldItemPosition].username == newUserList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldUserList[oldItemPosition]
        val newUser = newUserList[newItemPosition]
        return oldUser.username == newUser.username && oldUser.avatarUrl == oldUser.avatarUrl
    }
}
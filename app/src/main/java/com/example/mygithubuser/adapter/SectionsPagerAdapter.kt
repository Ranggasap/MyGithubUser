package com.example.mygithubuser.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mygithubuser.ui.UserFollowFragment

class SectionsPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    var username: String = ""

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = UserFollowFragment()
        fragment.arguments = Bundle().apply {
            putInt(UserFollowFragment.ARG_POSITION, position+1)
            putString(UserFollowFragment.ARG_USERNAME, username)
        }
        return fragment
    }

}
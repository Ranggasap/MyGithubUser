package com.example.mygithubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.adapter.UserFollowersAdapter
import com.example.mygithubuser.adapter.UserFollowingAdapter
import com.example.mygithubuser.data.response.UserFollowersGithubResponseItem
import com.example.mygithubuser.data.response.UserFollowingGithubResponseItem
import com.example.mygithubuser.databinding.FragmentUserFollowBinding
import com.example.mygithubuser.viewModel.FollowerViewModel
import com.example.mygithubuser.viewModel.FollowingViewModel
import com.google.android.material.snackbar.Snackbar

class UserFollowFragment : Fragment() {

    private lateinit var binding: FragmentUserFollowBinding
    private val viewModelFollower: FollowerViewModel by viewModels()
    private val viewModelFollowing: FollowingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserFollowBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(ARG_POSITION)
        val username = arguments?.getString(ARG_USERNAME)

        if(position == 1){
            followersFragment(username.toString())
        }else{
            followingFragment(username.toString())
        }
    }

    private fun followersFragment(dataUsername: String){

        if(viewModelFollower.username.value != dataUsername || viewModelFollower.username.value == null){
            viewModelFollower.setUsername(dataUsername)
        }

        viewModelFollower.listUserFollowers.observe(viewLifecycleOwner){ listUsersFollowers ->
            setUsersDataFollowers(listUsersFollowers)
        }

        viewModelFollower.snackbarText.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let{snackbarText ->
                Snackbar.make(
                    requireActivity().window.decorView.rootView,
                    snackbarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.recyclerViewFollow.addItemDecoration(itemDecoration)

        viewModelFollower.isLoading.observe(viewLifecycleOwner){isLoading ->
            showLoading(isLoading)
        }
    }

    private fun followingFragment(dataUsername: String){

        if(viewModelFollowing.username.value != dataUsername || viewModelFollowing.username.value == null){
            viewModelFollowing.setUsername(dataUsername)
        }

        viewModelFollowing.listUserFollowing.observe(viewLifecycleOwner){ listUserFollowing ->
            setUsersDataFollowing(listUserFollowing)
        }

        viewModelFollowing.snackbarText.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let{snackbarText ->
                Snackbar.make(
                    requireActivity().window.decorView.rootView,
                    snackbarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.recyclerViewFollow.addItemDecoration(itemDecoration)

        viewModelFollowing.isLoading.observe(viewLifecycleOwner){isLoading ->
            showLoading(isLoading)
        }
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setUsersDataFollowers(usersData: List<UserFollowersGithubResponseItem>){
        val adapter = UserFollowersAdapter()
        adapter.submitList(usersData)
        binding.recyclerViewFollow.adapter = adapter
    }

    private fun setUsersDataFollowing(usersData: List<UserFollowingGithubResponseItem>){
        val adapter = UserFollowingAdapter()
        adapter.submitList(usersData)
        binding.recyclerViewFollow.adapter = adapter
    }

    companion object {
        const val ARG_POSITION = "arg_position"
        const val ARG_USERNAME = "arg_username"
    }
}
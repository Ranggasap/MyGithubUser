package com.example.mygithubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.adapter.UsersAdapter
import com.example.mygithubuser.data.response.ItemsItem
import com.example.mygithubuser.databinding.FragmentFavoriteUsersBinding
import com.example.mygithubuser.viewModel.UserFavoriteViewModel
import com.example.mygithubuser.viewModelFactory.UserViewModelFactory

class FavoriteUsersFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteUsersBinding
    private lateinit var userFavoriteViewModel: UserFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteUsersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userFavoriteViewModel = obtainViewModel(requireActivity())

        userFavoriteViewModel.getAllUsers().observe(viewLifecycleOwner){users ->
            val items = arrayListOf<ItemsItem>()
            users.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl.toString())
                items.add(item)
            }
            setUsersData(items)
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFavoriteUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFavoriteUsers.addItemDecoration(itemDecoration)


    }

    private fun setUsersData(usersData: List<ItemsItem>){
        binding.pbFavoriteUsers.visibility = View.VISIBLE
        val adapter = UsersAdapter()
        adapter.submitList(usersData)
        binding.rvFavoriteUsers.adapter = adapter

        adapter.setOnItemClickCallback(object : UsersAdapter.OnItemClickCallback{
            override fun onItemClicked(user: ItemsItem) {
                val toDetailUserFragment = FavoriteUsersFragmentDirections.actionFavoriteUsersFragmentToDetailUserFragment()
                toDetailUserFragment.username = user.login
                toDetailUserFragment.avatarUrl = user.avatarUrl
                view?.findNavController()?.navigate(toDetailUserFragment)
            }

        })

        binding.pbFavoriteUsers.visibility = View.GONE
    }


    private fun obtainViewModel(activity: FragmentActivity): UserFavoriteViewModel{
        val factory = UserViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UserFavoriteViewModel::class.java)
    }
}
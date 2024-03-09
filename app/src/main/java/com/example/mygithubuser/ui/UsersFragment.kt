package com.example.mygithubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.R
import com.example.mygithubuser.adapter.UsersAdapter
import com.example.mygithubuser.data.response.ItemsItem
import com.example.mygithubuser.dataStore.SettingPreferences
import com.example.mygithubuser.dataStore.dataStore
import com.example.mygithubuser.databinding.FragmentUsersBinding
import com.example.mygithubuser.viewModel.ThemeViewModel
import com.example.mygithubuser.viewModel.UsersViewModel
import com.example.mygithubuser.viewModelFactory.ThemeViewModelFactory
import com.google.android.material.snackbar.Snackbar

class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val usersViewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = SettingPreferences.getInstance(requireContext().dataStore)
        val themeViewModel = ViewModelProvider(this, ThemeViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )

        themeViewModel.getThemeSettings().observe(viewLifecycleOwner){isDarkModeActive: Boolean ->
            if(isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        usersViewModel.listUsers.observe(viewLifecycleOwner){ listUsers ->
            setUsersData(listUsers)
        }

        usersViewModel.snackbarText.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let{snackbarText ->
                Snackbar.make(
                    requireActivity().window.decorView.rootView,
                    snackbarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener{textView, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    usersViewModel.searchUsername(searchView.text.toString())
                    false
                }
            searchBar.inflateMenu(R.menu.menu)
            searchBar.setOnMenuItemClickListener {menuItem ->
                when(menuItem.itemId){
                    R.id.theme_mode -> {
                        val toThemeModeFragment = UsersFragmentDirections.actionUsersFragmentToThemeModeFragment()
                        view?.findNavController()?.navigate(toThemeModeFragment)
                        true
                    }
                    R.id.favorite ->{
                        val toFavoriteUsersFragment = UsersFragmentDirections.actionUsersFragmentToFavoriteUsersFragment()
                        view?.findNavController()?.navigate(toFavoriteUsersFragment)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.recyclerView.addItemDecoration(itemDecoration)


        usersViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            showLoading(isLoading)
        }
    }

    private fun setUsersData(usersData: List<ItemsItem>){
        val adapter = UsersAdapter()
        adapter.submitList(usersData)
        binding.recyclerView.adapter = adapter

        adapter.setOnItemClickCallback(object : UsersAdapter.OnItemClickCallback{
            override fun onItemClicked(user: ItemsItem) {
                val toDetailUserFragment = UsersFragmentDirections.actionUsersFragmentToDetailUserFragment()
                toDetailUserFragment.username = user.login
                toDetailUserFragment.avatarUrl = user.avatarUrl
                view?.findNavController()?.navigate(toDetailUserFragment)
            }
        })
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}
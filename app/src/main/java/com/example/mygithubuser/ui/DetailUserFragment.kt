package com.example.mygithubuser.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mygithubuser.R
import com.example.mygithubuser.adapter.SectionsPagerAdapter
import com.example.mygithubuser.data.response.UserDetailGithubResponse
import com.example.mygithubuser.database.UserEntity
import com.example.mygithubuser.databinding.FragmentDetailUserBinding
import com.example.mygithubuser.viewModel.DetailUserViewModel
import com.example.mygithubuser.viewModel.UserFavoriteViewModel
import com.example.mygithubuser.viewModelFactory.UserViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserFragment : Fragment() {

    private lateinit var binding: FragmentDetailUserBinding
    private val detailUserViewModel: DetailUserViewModel by viewModels()
    private lateinit var userFavoriteViewModel: UserFavoriteViewModel
    private var userEntity: UserEntity? = null
    private var favorite: Boolean = false
    private var shareText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userFavoriteViewModel = obtainViewModel(requireActivity())
        userEntity = UserEntity()

        val dataUsername = DetailUserFragmentArgs.fromBundle(arguments as Bundle).username
        val dataAvatarUrl = DetailUserFragmentArgs.fromBundle(arguments as Bundle).avatarUrl

        if(detailUserViewModel.username.value != dataUsername || detailUserViewModel.username.value == null){
            detailUserViewModel.setUsername(dataUsername)
        }

        userFavoriteViewModel.getFavoriteUserByUsername(dataUsername).observe(viewLifecycleOwner){userEntity ->
            if(userEntity?.username == null){
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                favorite = false
            }else{
                binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
                favorite = true
            }
        }

        detailUserViewModel.detailUser.observe(viewLifecycleOwner){ userDetail ->
            setDetailUser(userDetail)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity())
        sectionsPagerAdapter.username = dataUsername
        val viewPager2: ViewPager2 = binding.viewPager
        viewPager2.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager2) {tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        detailUserViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            showLoading(isLoading)
        }

        binding.fabFavorite.setOnClickListener{
            userEntity.let {
                userEntity?.username = dataUsername
                userEntity?.avatarUrl = dataAvatarUrl
            }
            if(favorite){
                userFavoriteViewModel.delete(userEntity as UserEntity)
            }else{
                userFavoriteViewModel.insert(userEntity as UserEntity)
            }
        }

        binding.fabShare.setOnClickListener{
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(share, "Zoopedia"))
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): UserFavoriteViewModel{
        val factory = UserViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UserFavoriteViewModel::class.java)
    }

    private fun setDetailUser(userDetail: UserDetailGithubResponse){
        binding.tvUsername.text = userDetail.login
        Glide.with(binding.root.context)
            .load(userDetail.avatarUrl)
            .into(binding.ciUserAvatar)
        binding.tvName.text = userDetail.name
        binding.tvFollowing.text = "${userDetail.following}"
        binding.tvFollower.text = "${userDetail.followers}"
        binding.tvRepos.text = "${userDetail.publicRepos}"
        binding.tvFollower.visibility = View.VISIBLE
        binding.tvFollowing.visibility = View.VISIBLE
        binding.tvUsername.visibility = View.VISIBLE
        binding.tvName.visibility = View.VISIBLE
        binding.tvRepos.visibility = View.VISIBLE
        binding.tvReposTitle.visibility = View.VISIBLE
        binding.tvFollowerTitle.visibility = View.VISIBLE
        binding.tvFollowingTitle.visibility = View.VISIBLE

        shareText = "Hey coba cek aplikasi My Github Userku.\n\n" +
                "Aplikasi ini memaparkan informasi terkait salah satu user github yaitu ${userDetail.login} " +
                "dengan ${userDetail.followers} follower & ${userDetail.publicRepos} repository." +
                " Keren kan, coba download aplikasinya di link berikut: \n\n" +
                "https://github.com/Ranggasap/MyGithubUser"
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follower_tab,
            R.string.following_tab
        )
    }
}
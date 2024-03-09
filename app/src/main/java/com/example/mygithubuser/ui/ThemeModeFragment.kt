package com.example.mygithubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.mygithubuser.dataStore.SettingPreferences
import com.example.mygithubuser.dataStore.dataStore
import com.example.mygithubuser.databinding.FragmentThemeModeBinding
import com.example.mygithubuser.viewModel.ThemeViewModel
import com.example.mygithubuser.viewModelFactory.ThemeViewModelFactory

class ThemeModeFragment : Fragment() {

    private lateinit var binding: FragmentThemeModeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThemeModeBinding.inflate(inflater, container, false)
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
                binding.switchTheme.isChecked = true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            themeViewModel.saveThemeSetting(isChecked)
        }
    }
}
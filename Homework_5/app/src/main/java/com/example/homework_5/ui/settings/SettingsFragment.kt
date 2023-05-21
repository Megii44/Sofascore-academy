package com.example.homework_5.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.homework_5.R
import com.example.homework_5.databinding.FragmentSettingsBinding

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        val preferencesContainer = binding.preferencesContainer
        val preferencesView = super.onCreateView(inflater, preferencesContainer, savedInstanceState)
        preferencesContainer.addView(preferencesView)

        return view
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        // Check if the theme preference has been changed and update the theme accordingly
        when (key) {
            "theme" -> {
                updateTheme()
            }
            "language" -> {
                updateLanguage()
            }
            "unit_type" -> {
                updateUnitsSystem()
            }
        }
    }
    private fun updateTheme() {
        // Retrieve the theme preference from the shared preferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Set the appropriate theme based on the theme preference
        when (sharedPreferences.getString("theme", "Light")) {
            "Light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "Dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        // Recreate the activity to apply the new theme
        requireActivity().recreate()
    }

    private fun updateLanguage() {
        // Retrieve the theme preference from the shared preferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Set the appropriate theme based on the theme preference
        when (sharedPreferences.getString("theme", "Light")) {
            "Light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "Dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        // Recreate the activity to apply the new language
        requireActivity().recreate()
    }

    private fun updateUnitsSystem() {
        // Retrieve the theme preference from the shared preferences
        //val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Set the appropriate units type based on the units type preference

        // Recreate the activity to apply the new units system
        requireActivity().recreate()
    }
}
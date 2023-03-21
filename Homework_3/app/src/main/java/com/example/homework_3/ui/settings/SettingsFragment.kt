package com.example.homework_3.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.homework_3.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

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
        if (key == "theme") {
            updateTheme()
        } else if (key == "language") {
            updateLanguage()
        }
    }
    private fun updateTheme() {
        // Retrieve the theme preference from the shared preferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Set the appropriate theme based on the theme preference
        when (sharedPreferences.getString("theme", "light")) {
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        // Recreate the activity to apply the new theme
        requireActivity().recreate()
    }

    private fun updateLanguage() {
        // Retrieve the theme preference from the shared preferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Set the appropriate theme based on the theme preference
        when (sharedPreferences.getString("theme", "light")) {
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        // Recreate the activity to apply the new language
        requireActivity().recreate()
    }
}
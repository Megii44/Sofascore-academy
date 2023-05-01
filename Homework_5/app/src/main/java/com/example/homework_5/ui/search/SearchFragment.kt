package com.example.homework_5.ui.search

import com.example.homework_5.ui.city.CityActivity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.homework_5.adapter.RecentSearchesRecyclerAdapter
import com.example.homework_5.databinding.FragmentSearchBinding
import com.example.homework_5.model.CurrentLocationWeather
import com.example.homework_5.networking.Network
import com.example.homework_5.utils.getApiKey
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val toolbar = binding.toolbarSearch // Find the toolbar by its ID
        (activity as AppCompatActivity).setSupportActionBar(toolbar) // Set the toolbar as the action bar

        viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]

        // Create adapter for recycler view
        val emptyList = ArrayList<CurrentLocationWeather>()
        val recyclerAdapter = RecentSearchesRecyclerAdapter(requireContext(), emptyList) { selectedWeather ->
            // Create an intent to start the new activity
            val intent = Intent(requireContext(), CityActivity::class.java)

            // Pass the clicked object's data to the new activity
            intent.putExtra("weather", selectedWeather)

            // Start the new activity
            startActivity(intent)
        }

        binding.recentSearchesContainer.adapter = recyclerAdapter

        // Recent searches observer which updates the UI
        viewModel.recentSearches.observe(viewLifecycleOwner) { currentWeather ->
            // Update the UI when the product list changes
            // Update the content of the array adapter
            binding.recentSearchesContainer.adapter = RecentSearchesRecyclerAdapter(requireContext(), currentWeather) { selectedWeather ->
                // Create an intent to start the new activity
                val intent = Intent(requireContext(), CityActivity::class.java)

                // Convert the selectedWeather object to a JSON string
                val cityWeatherJson = Gson().toJson(selectedWeather)

                // Pass the JSON string to the new activity
                intent.putExtra("cityWeather", cityWeatherJson)

                // Start the new activity
                startActivity(intent)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAutoCompleteTextView()
    }

    private fun setupAutoCompleteTextView() {
        val autoCompleteTextView = binding.searchAutoCompleteTextView

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.threshold = 2

        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                if (query.length >= 2) { // You can adjust the minimum number of characters for the search
                    searchLocations(query, adapter)
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        })

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            // Handle the selection of a suggestion from the dropdown list
            // Get the selected location name
            val selectedLocation = adapter.getItem(position)

            // Get the current weather for the selected location
            if (selectedLocation != null) {
                getCurrentWeatherForLocation(selectedLocation)
            }
        }
    }

    private fun searchLocations(query: String, adapter: ArrayAdapter<String>) {
        val apiKey = getApiKey()

        CoroutineScope(Dispatchers.Main).launch {
            val response = Network().getService().searchLocations(apiKey, query)
            if (response.isSuccessful) {
                val locationSearchResponse = response.body()
                val locationNames = locationSearchResponse?.map { it.name } ?: listOf()
                adapter.clear()
                adapter.addAll(locationNames)
                adapter.notifyDataSetChanged()
            } else {
                // Handle the API error call
            }
        }
    }

    private fun getCurrentWeatherForLocation(query: String) {
        val apiKey = getApiKey()

        CoroutineScope(Dispatchers.Main).launch {
            val response = Network().getService().getCurrentWeatherForLocation(apiKey, query)
            if (response.isSuccessful) {
                val currentLocationWeatherResponse = response.body()
                val currentLocationWeather = currentLocationWeatherResponse?.let { CurrentLocationWeather(location = it.location, current = it.current) }
                viewModel.addRecentSearch(currentLocationWeather)
            } else {
                // Handle the API error call
            }
        }
    }

}
package com.example.homework_5.ui.search

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
import com.example.homework_5.ui.city.CityActivity
import com.google.gson.Gson

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            (activity as AppCompatActivity).setSupportActionBar(toolbarSearch)
        }

        viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]

        viewModel.recentSearches.observe(viewLifecycleOwner) { currentWeather ->
            binding.recentSearchesContainer.adapter = RecentSearchesRecyclerAdapter(requireContext(), currentWeather) { selectedWeather ->
                Intent(requireContext(), CityActivity::class.java).apply {
                    putExtra("cityWeather", Gson().toJson(selectedWeather))
                    startActivity(this)
                }
            }
        }

        setupAutoCompleteTextView()

        return binding.root
    }

    private fun setupAutoCompleteTextView() {
        ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line).let { adapter ->
            binding.searchAutoCompleteTextView.apply {
                setAdapter(adapter)
                threshold = 3

                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        val query = s.toString().trim()
                        if (query.length >= 3) viewModel.searchLocations(query, adapter)
                    }
                    override fun afterTextChanged(s: Editable) {}
                })

                setOnItemClickListener { _, _, position, _ ->
                    viewModel.getCurrentWeatherForLocation(adapter.getItem(position)?.trim().orEmpty())
                }
            }
        }
    }
}

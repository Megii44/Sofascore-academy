package com.example.homework_5.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homework_5.databinding.FragmentSearchBinding
import com.example.homework_5.networking.Network
import com.example.homework_5.utils.getApiKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val toolbar = binding.toolbarSearch // Find the toolbar by its ID
        (activity as AppCompatActivity).setSupportActionBar(toolbar) // Set the toolbar as the action bar

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
            print(position)
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




}
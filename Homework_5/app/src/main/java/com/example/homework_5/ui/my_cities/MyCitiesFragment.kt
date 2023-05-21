package com.example.homework_5.ui.my_cities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.homework_5.adapter.RecentSearchesRecyclerAdapter
import com.example.homework_5.databinding.FragmentMyCitiesBinding
import com.example.homework_5.ui.city.CityActivity
import com.google.gson.Gson

class MyCitiesFragment : Fragment() {

    private lateinit var binding: FragmentMyCitiesBinding
    private lateinit var viewModel: MyCitiesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMyCitiesBinding.inflate(inflater, container, false).apply {
            (activity as AppCompatActivity)
        }

        viewModel = ViewModelProvider(requireActivity())[MyCitiesViewModel::class.java]

        viewModel.myCities.observe(viewLifecycleOwner) { currentWeather ->
            binding.myCitiesRecyclerView.adapter = RecentSearchesRecyclerAdapter(requireContext(), currentWeather) { selectedWeather ->
                Intent(requireContext(), CityActivity::class.java).apply {
                    putExtra("cityWeather", Gson().toJson(selectedWeather))
                    startActivity(this)
                }
            }
        }

        return binding.root
    }
}

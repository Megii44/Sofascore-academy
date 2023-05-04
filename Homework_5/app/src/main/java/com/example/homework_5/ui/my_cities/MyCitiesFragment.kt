package com.example.homework_5.ui.my_cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.homework_5.databinding.FragmentMyCitiesBinding

class MyCitiesFragment : Fragment() {

    private var _binding: FragmentMyCitiesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myCitiesViewModel =
            ViewModelProvider(this)[MyCitiesViewModel::class.java]

        _binding = FragmentMyCitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val toolbar = binding.toolbar // Find the toolbar by its ID
        (activity as AppCompatActivity).setSupportActionBar(toolbar) // Set the toolbar as the action bar


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
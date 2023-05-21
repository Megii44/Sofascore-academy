package com.example.minisofascore.ui.american_football

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minisofascore.R

class AmericanFootballFragment : Fragment() {

    companion object {
        fun newInstance() = AmericanFootballFragment()
    }

    private lateinit var viewModel: AmericanFootballViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_american_football, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AmericanFootballViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
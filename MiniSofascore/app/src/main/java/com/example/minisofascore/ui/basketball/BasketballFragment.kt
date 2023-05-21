package com.example.minisofascore.ui.basketball

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minisofascore.R

class BasketballFragment : Fragment() {

    companion object {
        fun newInstance() = BasketballFragment()
    }

    private lateinit var viewModel: BasketballViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_basketball, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BasketballViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
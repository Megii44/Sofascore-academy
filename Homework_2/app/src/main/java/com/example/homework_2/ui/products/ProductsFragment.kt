package com.example.homework_2.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework_2.MainActivity
import com.example.homework_2.R
import com.example.homework_2.viewmodels.ProductsViewModel

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private lateinit var viewModel: ProductsViewModel
    private val mainActivity = requireActivity() as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        displayProducts()

        return view
    }

    fun displayProducts() {
        val products = mainActivity.getAllProducts()

        for(p in products) {

        }
    }
}
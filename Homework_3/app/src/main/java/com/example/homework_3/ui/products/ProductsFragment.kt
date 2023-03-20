package com.example.homework_3.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.homework_3.model.Product
import com.example.homework_3.viewmodels.ProductsViewModel
import com.example.homework_3.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment and initialize the viewModel
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]

        val productsContainer = binding.productsContainer

        // Create an ArrayAdapter
        val emptyList = ArrayList<Product>()
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, emptyList)

        // Set the adapter to the ListView
        productsContainer.adapter = arrayAdapter

        // Products observer which updates the UI
        val productsObserver = Observer<ArrayList<Product>> { products ->
            // Update the UI when the product list changes
            // Update the content of the array adapter
            arrayAdapter.addAll(products)
        }

        // Observe the LiveData, passing in main activity as the LifecycleOwner and the observer.
        viewModel.products.observe(viewLifecycleOwner, productsObserver)

        return binding.root
    }
}

package com.example.homework_3.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework_3.adapter.ProductsRecyclerAdapter
import com.example.homework_3.viewmodels.ProductsViewModel
import com.example.homework_3.databinding.FragmentProductsBinding
import com.example.homework_3.model.Product

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

        // Create adapter for recycler view
        val emptyList = ArrayList<Product>()
        val adapter = ProductsRecyclerAdapter(requireContext(), emptyList)
        binding.productsContainer.adapter = adapter

        // Products observer which updates the UI
        viewModel.products.observe(viewLifecycleOwner) { products ->
            // Update the UI when the product list changes
            // Update the content of the array adapter
            binding.productsContainer.adapter = ProductsRecyclerAdapter(requireContext(), products)
        }

        return binding.root
    }
}

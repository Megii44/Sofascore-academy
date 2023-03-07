package com.example.homework_2.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.homework_2.R
import com.example.homework_2.model.Product
import com.example.homework_2.viewmodels.ProductsViewModel

class ProductsFragment : Fragment() {

    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]

        val productsContainer = view.findViewById<LinearLayout>(R.id.productsContainer)

        // Products observer which updates the UI
        val productsObserver = Observer<ArrayList<Product>> { products ->
            // Update the UI when the product list changes
            productsContainer.removeAllViews()
            products.forEach { product ->
                val textView = TextView(context)
                textView.text = product.toString()
                productsContainer.addView(textView)
            }
        }

        // Observe the LiveData, passing in main activity as the LifecycleOwner and the observer.
        viewModel.products.observe(viewLifecycleOwner, productsObserver)

        return view
    }
}

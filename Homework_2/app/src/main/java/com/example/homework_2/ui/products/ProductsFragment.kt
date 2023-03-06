package com.example.homework_2.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.homework_2.MainActivity
import com.example.homework_2.R
import com.example.homework_2.model.Product
import com.example.homework_2.viewmodels.AddProductViewModel
import com.example.homework_2.viewmodels.ProductsViewModel

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]

        val products = viewModel.getProducts()

        val container = view.findViewById<LinearLayout>(R.id.productsContainer)

        for(product in products) {
            val textView = TextView(context)
            textView.text = "ITEM\n" +
                            "Name = ${product.name}\n"+
                            "Description = ${product.description}\n" +
                            "Brand = ${product.brand}\n" +
                            "Category = ${product.category}\n" +
                            "Product type = ${product.productType}\n" +
                            "Style = ${product.style}\n" +
                            "Color = ${product.color}\n" +
                            "Material = ${product.material}\n" +
                            "Size = ${product.size}\n" +
                            "Price = ${product.price}\n"
            container.addView(textView)
        }

        return view
    }

}
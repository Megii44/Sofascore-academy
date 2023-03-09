package com.example.homework_2.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.example.homework_2.R
import com.example.homework_2.model.Product
import com.example.homework_2.viewmodels.ProductsViewModel

class AddProductFragment : Fragment() {

    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]

        val submitButton = view.findViewById<Button>(R.id.btn_submit)
        val formContainer = view.findViewById<LinearLayout>(R.id.form_container)

        submitButton.setOnClickListener {
            val name = view.findViewById<EditText>(R.id.editTextName).text.toString()
            val description = view.findViewById<EditText>(R.id.editTextDescription).text.toString()
            val brand = view.findViewById<EditText>(R.id.editTextBrand).text.toString()
            val category = view.findViewById<EditText>(R.id.editTextCategory).text.toString()
            val productType = view.findViewById<EditText>(R.id.editTextProductType).text.toString()
            val style = view.findViewById<EditText>(R.id.editTextStyle).text.toString()
            val color = view.findViewById<EditText>(R.id.editTextColor).text.toString()
            val material = view.findViewById<EditText>(R.id.editTextMaterial).text.toString()
            val size = view.findViewById<EditText>(R.id.editTextSize).text.toString()
            val price = view.findViewById<EditText>(R.id.editTextPrice).text.toString()

            val newProduct = Product(name, description, brand, category,
                productType, style, color, material,
                size, price)

            viewModel.addProduct(product = newProduct)

            // Reset the input fields
            formContainer.children.filterIsInstance<EditText>().forEach {
                it.text.clear()
            }
        }

        return view
    }

}
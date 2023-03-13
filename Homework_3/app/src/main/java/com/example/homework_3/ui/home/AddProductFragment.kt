package com.example.homework_3.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.example.homework_3.model.Product
import com.example.homework_3.viewmodels.ProductsViewModel
import com.example.homework_3.R

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
        val formContainer = view.findViewById<ConstraintLayout>(R.id.form_container)

        submitButton.setOnClickListener {
            val name = view.findViewById<EditText>(R.id.name_edit_text).text.toString()
            val description = view.findViewById<EditText>(R.id.description_edit_text).text.toString()
            val brand = view.findViewById<EditText>(R.id.brand_edit_text).text.toString()
            val category = view.findViewById<EditText>(R.id.category_edit_text).text.toString()
            val productType = view.findViewById<EditText>(R.id.product_type_edit_text).text.toString()
            val style = view.findViewById<EditText>(R.id.style_edit_text).text.toString()
            val color = view.findViewById<EditText>(R.id.color_edit_text).text.toString()
            val material = view.findViewById<EditText>(R.id.material_edit_text).text.toString()
            val size = view.findViewById<EditText>(R.id.size_edit_text).text.toString()
            val price = view.findViewById<EditText>(R.id.price_edit_text).text.toString()

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
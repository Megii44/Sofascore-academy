package com.example.homework_3.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.example.homework_3.model.Product
import com.example.homework_3.viewmodels.ProductsViewModel
import com.example.homework_3.R
import com.example.homework_3.enum.SizeEnum

class AddProductFragment : Fragment() {

    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]

        val sizes = SizeEnum.getValues()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sizes)
        val sizeSpinner = view.findViewById<Spinner>(R.id.size_spinner)
        sizeSpinner.adapter = adapter

        sizeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Do something when an item is selected
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do something when nothing is selected
            }
        }

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
            val selectedRadioButtonId = view.findViewById<RadioGroup>(R.id.material_radio_group).checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedRadioButtonId)
            val material = selectedRadioButton.text.toString()
            val size = sizeSpinner.selectedItem.toString()
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
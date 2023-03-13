package com.example.homework_3.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.example.homework_3.model.Product
import com.example.homework_3.viewmodels.ProductsViewModel
import com.example.homework_3.databinding.FragmentAddProductBinding
import com.example.homework_3.enum.SizeEnum

class AddProductFragment : Fragment() {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding: FragmentAddProductBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentAddProductBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialize the ViewModel
        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]

        // Initialize the sizes dropdown list
        val sizes = SizeEnum.getValues()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sizes)
        val sizeSpinner = binding.sizeSpinner
        sizeSpinner.adapter = adapter

        sizeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Do something when an item is selected
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do something when nothing is selected
            }
        }

        val submitButton = binding.btnSubmit
        val formContainer = binding.formContainer

        submitButton.setOnClickListener {
            val selectedRadioButtonId = binding.materialRadioGroup.checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedRadioButtonId)?.text.toString()
            val size = sizeSpinner.selectedItem.toString()

            val newProduct = Product(binding.nameEditText.text.toString(), binding.descriptionEditText.text.toString(), binding.brandEditText.text.toString(),
                binding.categoryEditText.text.toString(), binding.productTypeEditText.text.toString(), binding.styleEditText.text.toString(),
                binding.colorEditText.text.toString(),
                selectedRadioButton ,
                size, binding.priceEditText.text.toString())

            viewModel.addProduct(product = newProduct)

            // Reset the input fields
            formContainer.children.filterIsInstance<EditText>().forEach {
                it.text.clear()
            }
        }

        return view
    }

}
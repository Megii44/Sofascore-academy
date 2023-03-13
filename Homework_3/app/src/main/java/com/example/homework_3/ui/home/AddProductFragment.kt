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

        // Initialize the sizes dropdown list with Array adapter
        val sizes = SizeEnum.getValues()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sizes)
        val sizeSpinner = binding.sizeSpinner
        sizeSpinner.adapter = adapter

        binding.btnSubmit.setOnClickListener {
            var isValid = true
            // Validate all fields
            binding.formContainer.children.filterIsInstance<EditText>().forEach {
                if (!validate(it.text.toString())) {
                    it.error = "This field is required"
                    isValid = false
                }
            }

            // Validate radio button
            if (binding.materialRadioGroup.checkedRadioButtonId == -1) {
                binding.materialRadioGroup.requestFocus()
                Toast.makeText(requireContext(), "Please select a material", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if(isValid) {
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
                binding.formContainer.children.filterIsInstance<EditText>().forEach {
                    it.text.clear()
                }

                // Reset radio buttons
                binding.materialRadioGroup.clearCheck()

                // Reset spinners
                binding.sizeSpinner.setSelection(-1)
            }
        }

        return view
    }

    private fun validate(str: String?): Boolean {
        if (str == null || str.isEmpty()) {
            return false
        }
        return true
    }
}
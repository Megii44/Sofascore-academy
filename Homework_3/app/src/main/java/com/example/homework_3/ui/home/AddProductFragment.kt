package com.example.homework_3.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.descendants
import androidx.lifecycle.ViewModelProvider
import com.example.homework_3.R
import com.example.homework_3.model.Product
import com.example.homework_3.viewmodels.ProductsViewModel
import com.example.homework_3.databinding.FragmentAddProductBinding
import com.example.homework_3.enum.SizeEnum
import com.example.homework_3.views.EditTextWithLabelView

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
            // Validate all edit text fields
            binding.formContainer.descendants.filterIsInstance<EditTextWithLabelView>().forEach {
                if (!it.validate()) {
                    isValid = false
                }
            }

            // Validate radio button
            if (binding.materialRadioGroup.checkedRadioButtonId == -1) {
                binding.materialRadioGroup.requestFocus()
                Toast.makeText(requireContext(), R.string.material_error.toString() , Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if(isValid) {
                val selectedRadioButtonId = binding.materialRadioGroup.checkedRadioButtonId
                val selectedRadioButton = view.findViewById<RadioButton>(selectedRadioButtonId)?.text.toString()
                val size = SizeEnum.fromStringToSizeEnum(sizeSpinner.selectedItem.toString())

                val newProduct = Product(binding.nameInput.getEditTextValue(), binding.descriptionInput.getEditTextValue(),
                    binding.brandInput.getEditTextValue(), binding.categoryInput.getEditTextValue(),
                    binding.productTypeInput.getEditTextValue(), binding.styleInput.getEditTextValue(),
                    binding.colorInput.getEditTextValue(), selectedRadioButton,
                    size, binding.priceInput.getEditTextValue(), binding.productImageInput.getEditTextValue())

                viewModel.addProduct(product = newProduct)

                // Reset the input fields
                binding.formContainer.descendants.filterIsInstance<EditText>().forEach {
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
}
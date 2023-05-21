package com.example.homework_3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.homework_3.R
import com.example.homework_3.databinding.ActivityProductItemWithDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductItemWithDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductItemWithDetailsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_product_item_with_details)

        binding.productName.text = intent.getStringExtra("product_name")
        binding.productDescription.text = intent.getStringExtra("product_description")
        binding.productBrand.text = intent.getStringExtra("product_brand")
        binding.productCategory.text = intent.getStringExtra("product_category")
        binding.productType.text = intent.getStringExtra("product_type")
        binding.productStyle.text = intent.getStringExtra("product_style")
        binding.productColor.text = intent.getStringExtra("product_color")
        binding.productMaterial.text = intent.getStringExtra("product_material")
        binding.productSize.text = intent.getStringExtra("product_size")
        binding.productPrice.text = intent.getStringExtra("product_price")
        binding.productImage.load(intent.getStringExtra("product_image_url"))
    }
}
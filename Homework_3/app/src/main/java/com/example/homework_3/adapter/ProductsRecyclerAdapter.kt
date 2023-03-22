package com.example.homework_3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework_3.R
import com.example.homework_3.databinding.ProductItemBinding
import com.example.homework_3.model.Product

class ProductsRecyclerAdapter(private val context: Context, private val productsList: ArrayList<Product>):
    RecyclerView.Adapter<ProductsRecyclerAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ProductItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]

        holder.binding.apply {
            productName.text = product.name
            productDescription.text = product.description
            productPrice.text = product.price
            productImage.load(product.productImageUrl)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}
package com.example.homework_3.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework_3.R
import com.example.homework_3.databinding.ProductItemBinding
import com.example.homework_3.enum.SizeEnum
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
        holder.binding.root.backgroundTintList = ColorStateList.valueOf(
            when(product.size) {
                SizeEnum.XXS -> context.getColor(R.color.purple_200)
                SizeEnum.XS -> context.getColor(R.color.purple_200)
                SizeEnum.S -> context.getColor(R.color.purple_200)
                SizeEnum.M -> context.getColor(R.color.purple_200)
                SizeEnum.L -> context.getColor(R.color.purple_200)
                SizeEnum.XL -> context.getColor(R.color.purple_200)
                SizeEnum.XXL -> context.getColor(R.color.purple_200)
            }
        )
        holder.binding.apply {
            productName.text = product.name
            productDescription.text = product.description
            productBrand.text = product.brand
            productCategory.text = product.category
            productType.text = product.productType
            productStyle.text = product.style
            productColor.text = product.color
            productMaterial.text = product.material
            productSize.text = product.size.toString()
            productPrice.text = product.price
            productImage.load(product.productImageUrl)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}
package com.tops.androidfundamentals.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.androidfundamentals.databinding.ProductRowItemBinding
import com.tops.androidfundamentals.model.Product

class MyAdapter(private val products: List<Product>): RecyclerView.Adapter<MyAdapter.ProductViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding = ProductRowItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        val products = products[position]
        holder.binding.textview.text = products.title

    }

    override fun getItemCount(): Int {
        return products.size

    }

    class ProductViewHolder(val binding: ProductRowItemBinding): RecyclerView.ViewHolder(binding.root) {

    }


}
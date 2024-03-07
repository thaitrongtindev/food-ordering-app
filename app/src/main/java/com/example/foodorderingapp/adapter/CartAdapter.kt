package com.example.foodorderingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.CartItemBinding

class CartAdapter(private val cartItems: MutableList<String>, private val cartItemPrice: MutableList<String>, private val cartImage: MutableList<Int>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private val itemQuantities = IntArray(cartItems.size)
   class CartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(cartItems: String, cartItemPrice: String, imageCart: Int, quan: Int) {
            binding.tvCartFoodName.text = cartItems
           binding.tvCartPrice.text = cartItemPrice
           binding.imageView.setImageResource(imageCart)
           binding.tvItemCartQuantities.text = quan.toString()

       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val quan = itemQuantities[position]
        val cartItems = cartItems[position]
        val cartItemPrice = cartItemPrice[position]
        val imageCart = cartImage[position]
        holder.bind(cartItems,cartItemPrice, imageCart, quan)
    }
}
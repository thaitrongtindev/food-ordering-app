package com.example.foodorderingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.CartItemBinding

class CartAdapter(private val cartItems: MutableList<String>, private val cartItemPrice: MutableList<String>, private val cartImage: MutableList<Int>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private val itemQuantities = IntArray(cartItems.size)
  inner class CartViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(position: Int) {
           val quan = itemQuantities[position]
           val cartItems = cartItems[position]
           val cartItemPrice = cartItemPrice[position]
           val imageCart = cartImage[position]


            binding.tvCartFoodName.text = cartItems
           binding.tvCartPrice.text = cartItemPrice
           binding.imageView.setImageResource(imageCart)
           binding.tvItemCartQuantities.text = quan.toString()

           binding.minusButton.setOnClickListener {
                   if (itemQuantities[position] > 0) {
                       itemQuantities[position]--
                       binding.tvItemCartQuantities.text = itemQuantities[position].toString()

                   }

           }
           binding.plusButton.setOnClickListener {
                    if (itemQuantities[position] < 10) {
                        itemQuantities[position]++
                        binding.tvItemCartQuantities.text = itemQuantities[position].toString()
                    }

           }
           binding.btnTrash.setOnClickListener {

                    val adapterPosition = adapterPosition
                    deleteItem(adapterPosition)

           }


       }
   }

    private fun deleteItem(adapterPosition: Int) {
        cartItems.removeAt(adapterPosition)
        cartImage.removeAt(adapterPosition)
        cartItemPrice.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
        notifyItemRangeChanged(adapterPosition, cartItems.size)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        holder.bind(position)




    }

}
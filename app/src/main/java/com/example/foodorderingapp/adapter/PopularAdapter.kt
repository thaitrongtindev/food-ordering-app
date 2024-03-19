package com.example.foodorderingapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.DetailsActivity
import com.example.foodorderingapp.databinding.PopularItemBinding
import com.example.foodorderingapp.model.MenuItem

class PopularAdapter(
    private val menuItemList: MutableList<MenuItem>, private val requireContext: Context
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(private val binding: PopularItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            foodName: String?,
            foodPrice: String?,
            foodImage: String?,
            foodIngredient: String?,
            foodDescriptiom: String?
        ) {
            binding.tvFoodNamePopular.text = foodName
            binding.tvPricePopular.text = foodName
            val uri = Uri.parse(foodImage)
            Glide.with(requireContext).load(uri).into(binding.foodImage)
        }
//        fun bind(item: String, price: String, image: Int) {
//            binding.tvFoodNamePopular.text = item
//            binding.tvPricePopular.text = price
//            binding.imageView.setImageResource(image)
//
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val menuItems = menuItemList[position]
        val foodName = menuItems.foodName
        val foodPrice = menuItems.foodPrice
        val foodImage = menuItems.foodImage
        val foodIngredient = menuItems.foodIngredient
        val foodDescription = menuItems.foodDescription
        holder.bind(foodName, foodPrice, foodImage, foodIngredient, foodDescription)

        // set on click
        holder.itemView.setOnClickListener() {
            val intent = Intent(requireContext, DetailsActivity::class.java).apply {
                putExtra("MenuItemName", foodName)
                putExtra("MenuItemImage", foodImage)
                putExtra("MenuItemDescription", foodDescription)
                putExtra("MenuItemIngredient", foodIngredient)
                putExtra("MenuItemPrice", foodPrice)
            }
            requireContext.startActivity(intent)
        }
    }
}
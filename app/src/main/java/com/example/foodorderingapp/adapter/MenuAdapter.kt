package com.example.foodorderingapp.adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.MenuItemBinding

class MenuAdapter( val MenuItems: MutableList<String>,  val MenuPrice: MutableList<String>, val  MenuImage: MutableList<Int>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

   inner class MenuViewHolder(val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                tvFoodNameMenu.text = MenuItems[position]
                tvPriceMenu.text = MenuPrice[position]
                menuImage.setImageResource(MenuImage[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context))
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return MenuItems.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }
}
package com.example.foodorderingapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.DetailsActivity
import com.example.foodorderingapp.databinding.MenuItemBinding

class MenuAdapter( val MenuItems: MutableList<String>,  val MenuPrice: MutableList<String>, val  MenuImage: MutableList<Int>
    ,  val requireContext: Context
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private val itemClickListener: OnClickListener ?= null
   inner class MenuViewHolder(val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener() {
                val position = adapterPosition
                itemClickListener?.onItemClick(position)

                val intent = Intent(requireContext, DetailsActivity::class.java)
                intent.putExtra("MenuItems", MenuItems[position])
                intent.putExtra("MenuImage", MenuImage[position])
                requireContext.startActivity(intent)

            }
        }
        fun bind(position: Int) {
            binding.apply {
                tvFoodNameMenu.text = MenuItems[position]
                tvPriceMenu.text = MenuPrice[position]
                menuImage.setImageResource(MenuImage[position])


            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return MenuItems.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }
    interface OnClickListener{
        fun onItemClick(position: Int) {

        }}
}





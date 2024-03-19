package com.example.foodorderingapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.DetailsActivity
import com.example.foodorderingapp.databinding.MenuItemBinding
import com.example.foodorderingapp.model.MenuItem

class MenuAdapter( val menuItemList: List<MenuItem>
    ,  val requireContext: Context
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
   inner class MenuViewHolder(val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener() {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    openDetailsActivity(position)
                }

            }
        }

       private fun openDetailsActivity(position: Int) {
           val menuItem : MenuItem = menuItemList[position]
           val intent = Intent(requireContext, DetailsActivity::class.java).apply {
               putExtra("MenuItemName", menuItem.foodName)
               putExtra("MenuItemImage", menuItem.foodImage)
               putExtra("MenuItemDescription", menuItem.foodDescription)
               putExtra("MenuItemIngredient", menuItem.foodIngredient)
               putExtra("MenuItemPrice", menuItem.foodPrice)
           }
           requireContext.startActivity(intent)
       }

       fun bind(position: Int) {
            binding.apply {
                tvFoodNameMenu.text = menuItemList[position].foodName
                tvPriceMenu.text = menuItemList[position].foodPrice
                val uriString = menuItemList[position].foodImage
                val uri:Uri = Uri.parse(uriString)
                Glide.with(requireContext).load(uri).into(menuImage)



            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }
    interface OnClickListener{
        fun onItemClick(position: Int) {

        }}
}





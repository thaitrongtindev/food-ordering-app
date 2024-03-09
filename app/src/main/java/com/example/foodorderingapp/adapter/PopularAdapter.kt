package com.example.foodorderingapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.DetailsActivity
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.PopularItemBinding

class PopularAdapter(
    private val items: List<String>,
    private val price: List<String>,
    private val image: List<Int>, private val requireContext: Context
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    class PopularViewHolder(private val binding: PopularItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, price: String, image: Int) {
            binding.tvFoodNamePopular.text = item
            binding.tvPricePopular.text = price
            binding.imageView.setImageResource(image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        val price = price[position]
        val image = image[position]

        holder.bind(item, price, image)

        // set on click
        holder.itemView.setOnClickListener() {
            val intent = Intent(requireContext, DetailsActivity::class.java)
            intent.putExtra("MenuItems", item)
            intent.putExtra("MenuImage", image)
            requireContext.startActivity(intent)
        }
    }
}
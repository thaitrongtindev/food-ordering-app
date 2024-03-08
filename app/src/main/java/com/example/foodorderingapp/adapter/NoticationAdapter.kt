package com.example.foodorderingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.NotificationItemBinding

class NoticationAdapter(private val notication : List<String>, private val notifcaitonImages : List<Int>)
    : RecyclerView.Adapter<NoticationAdapter.NotificationViewHolder>(){

        inner class NotificationViewHolder(val binding: NotificationItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(position: Int) {
                val notificationName = notication[position]
                val noticationImages = notifcaitonImages[position]

                binding.apply {
                    tvNotification.text = notificationName
                    notifiicationImage.setImageResource(noticationImages)
                    }


            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = NotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun getItemCount() : Int = notication.size


    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(position)
    }
}
package com.example.foodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.foodorderingapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)


        val foodName = intent.getStringExtra("MenuItems")
        val foodImage = intent.getIntExtra("MenuImage", 0)

        binding.apply {
            tvFoodName.text = foodName
            imageView.setImageResource(foodImage)
        }

    }
}
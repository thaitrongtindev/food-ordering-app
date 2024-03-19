package com.example.foodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.foodorderingapp.databinding.ActivityDetailsBinding
import com.example.foodorderingapp.model.CartItems
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var foodName: String? = null
    private var foodPrice: String? = null
    private var foodImage: String? = null
    private var foodDescription: String? = null
    private var foodIngredient: String? = null

    private lateinit var database : FirebaseDatabase
    private lateinit var auh : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        auh = Firebase.auth

        foodName = intent.getStringExtra("MenuItemName")
        foodPrice = intent.getStringExtra("MenuItemPrice")
        foodImage = intent.getStringExtra("MenuItemImage")
        foodDescription = intent.getStringExtra("MenuItemDescription")
        foodIngredient = intent.getStringExtra("MenuItemIngredient")

        binding.apply {
            tvFoodName.text = foodName
            tvDescription.text = foodDescription
            tv8.text = foodIngredient
            Glide.with(binding.root).load(foodImage).into(imageView)

            imageBtn.setOnClickListener {
                finish()
            }
        }
        binding.btnAddToChart.setOnClickListener {
            addItemToCart()
        }


    }

    private fun addItemToCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userId = auh.currentUser?.uid

        //create a cartItem object
        val cartItem = CartItems(foodName.toString(), foodPrice.toString(), foodDescription.toString(),
            foodImage.toString(), 1)

        //save data to cart to firebase db
        database.child("user").child(userId.toString()).child("CartItems").push().setValue(cartItem)
            .addOnCompleteListener {
                task -> if (task.isSuccessful) {
                    Toast.makeText(this, "Items add into cart successfully", Toast.LENGTH_SHORT).show()
            }
            }.addOnFailureListener {
                Toast.makeText(this, "Items add into cart failed", Toast.LENGTH_SHORT).show()

            }
    }
}
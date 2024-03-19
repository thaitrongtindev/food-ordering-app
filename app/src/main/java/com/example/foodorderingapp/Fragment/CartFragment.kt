package com.example.foodorderingapp.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.PayOutActivity
import com.example.foodorderingapp.R
import com.example.foodorderingapp.adapter.CartAdapter
import com.example.foodorderingapp.databinding.FragmentCartBinding
import com.example.foodorderingapp.model.CartItems
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CartFragment : Fragment() {
// TODO: Rename and change types of parameters


    private lateinit var binding: FragmentCartBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var foodName: MutableList<String>
    private lateinit var foodPrices: MutableList<String>
    private lateinit var foodDescription: MutableList<String>
    private lateinit var foodImageUri: MutableList<String>
    private lateinit var foodIngredients: MutableList<String>
    private lateinit var quantity: MutableList<Int>
    private lateinit var userId: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = FirebaseAuth.getInstance()
        retrieveCartItems()


        binding.btnProcessed.setOnClickListener() {
            startActivity(Intent(requireContext(), PayOutActivity::class.java))
        }


    }

    private fun retrieveCartItems() {
        // database reference to the firebase
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid.toString()
        val foodReference: DatabaseReference = database.reference
            .child("user").child(userId).child("CartItems")

        // list to store cart item
        foodName = mutableListOf()
        foodPrices = mutableListOf()
        foodDescription = mutableListOf()
        foodImageUri = mutableListOf()
        foodIngredients = mutableListOf()
        quantity = mutableListOf()

        // fetch data from the database
        foodReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    val cartItems = foodSnapshot.getValue(CartItems::class.java)
                    cartItems?.foodName?.let {
                        foodName.add(it)
                    }
                    cartItems?.foodPrice?.let {
                        foodPrices.add(it)
                    }
                    cartItems?.foodDescription?.let {
                        foodDescription.add(it)
                    }
                    cartItems?.foodImage?.let {
                        foodImageUri.add(it)
                    }
                    cartItems?.foodIngredient?.let {
                        foodIngredients.add(it)
                    }

                    cartItems?.foodQuantity?.let {
                        quantity.add(it)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun setAdapter() {
        val adapterCart = context?.let {
            CartAdapter(
                foodName,
                foodPrices,
                foodImageUri,
                foodDescription,
                quantity,
                foodIngredients,
                it.applicationContext
            )
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterCart
        }
    }


}



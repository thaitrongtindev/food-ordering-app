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


class CartFragment : Fragment() {
// TODO: Rename and change types of parameters


    private lateinit var binding: FragmentCartBinding
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


        val cartFoodName = listOf("Burger", "Sandwich", "Mono", "Item", "Banh Mi")

        val cartItemPrice = listOf("$5", "$7", "$4", "$10", "$20")
        val cartFoodImages = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5
        )

        val adapterCart = CartAdapter(ArrayList(cartFoodName),ArrayList(cartItemPrice), ArrayList(cartFoodImages))
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterCart
        }

        binding.btnProcessed.setOnClickListener() {
            startActivity(Intent(requireContext(), PayOutActivity::class.java))
        }


    }


    }



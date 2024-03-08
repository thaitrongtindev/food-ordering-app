package com.example.foodorderingapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.adapter.BuyAgainAdapter
import com.example.foodorderingapp.databinding.FragmentHistoryBinding
import com.example.foodorderingapp.databinding.FragmentHomeBinding


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_history, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val foodName = mutableListOf<String>("Nuoc1", "Nuoc 2", "Nuoc 3", "Nuoc 4", "Nuoc 5" )
        val price = mutableListOf<String>("$4", "$ 2", "$ 3", "$ 4", "$ 4" )
        val images = mutableListOf<Int>(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5,
        )

        buyAgainAdapter = BuyAgainAdapter(foodName, price, images)
        binding.historyRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = buyAgainAdapter
        }
    }


}
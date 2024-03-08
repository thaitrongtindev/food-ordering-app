package com.example.foodorderingapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.adapter.MenuAdapter
import com.example.foodorderingapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: MenuAdapter
    private val filterMenuFoodName = mutableListOf<String>()
    private val filterMenuFoodPrice = mutableListOf<String>()
    private val filterMenuFoodImage = mutableListOf<Int>()
    val menuFoodName = listOf("Burger", "Sandwich", "Mono", "Item", "Banh Mi")

    val menuItemPrice = listOf("$5", "$7", "$4", "$10", "$20")
    val menuFoodImages = listOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu5
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater,  container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        searchAdapter = MenuAdapter(filterMenuFoodName, filterMenuFoodPrice, filterMenuFoodImage)
        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }

        binding.seaarchView.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Xử lý tìm kiếm khi người dùng nhấn enter hoặc nút tìm kiếm
           //     filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                    // Xử lý sự kiện thay đổi văn bản trong SearchView
                filterMenuItems(newText)
                return true
            }
        });

        showAllMenu(menuFoodName, menuItemPrice, menuFoodImages)

    }

    private fun showAllMenu(
        menuFoodName: List<String>,
        menuItemPrice: List<String>,
        menuFoodImages: List<Int>
    ) {
        filterMenuFoodImage.clear()
        filterMenuFoodPrice.clear()
        filterMenuFoodName.clear()

        filterMenuFoodPrice.addAll(menuItemPrice)
        filterMenuFoodName.addAll(menuFoodName)
        filterMenuFoodImage.addAll(menuFoodImages)
        searchAdapter.notifyDataSetChanged()
    }

    private fun filterMenuItems(query: String) {
        filterMenuFoodName.clear()
        filterMenuFoodPrice.clear()
        filterMenuFoodImage.clear()

//        for (i in menuFoodName.indices) {
//            if (menuFoodName[i].contains(query, ignoreCase = true)) {
//                filterMenuFoodName.add(menuFoodName[i])
//                filterMenuFoodPrice.add(menuItemPrice[i])
//                filterMenuFoodImage.add(menuFoodImages[i])
//            }
//        }
        menuFoodName.forEachIndexed {
            i, foodName ->
            if (foodName.contains(query, ignoreCase = true)) {
                filterMenuFoodName.add(foodName)
                filterMenuFoodPrice.add(menuItemPrice[i])
                filterMenuFoodImage.add(menuFoodImages[i])
            }
        }

        searchAdapter.notifyDataSetChanged()
    }
}
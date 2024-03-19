package com.example.foodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.foodorderingapp.databinding.ActivityChooseLocationBinding

class ChooseLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_location)

        val locationList = arrayOf("TPHCM", "HN", "DN", "BT")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationList)

        binding.listOfLocation.setAdapter(adapter)


    }
}
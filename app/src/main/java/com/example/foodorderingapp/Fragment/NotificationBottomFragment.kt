package com.example.foodorderingapp.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R
import com.example.foodorderingapp.adapter.NotificationAdapter
import com.example.foodorderingapp.databinding.FragmentNotificationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NotificationBottomFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotificationBottomBinding
    private lateinit var notificationAdapter: NotificationAdapter

    private var notifications: List<String>? = null
    private var notificationImages: List<Int>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification_bottom, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        notifications = listOf(
            "Your order has been Canceled Successfully",
            "Order has been taken by the driver",
            "Congrats Your Order Placed"
        )
        notificationImages = listOf(
            R.drawable.sademoji, R.drawable.truck, R.drawable.congrats
        )

        notificationAdapter = NotificationAdapter(ArrayList(notifications), ArrayList(notificationImages))

        binding.notificationRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notificationAdapter
        }
    }
}


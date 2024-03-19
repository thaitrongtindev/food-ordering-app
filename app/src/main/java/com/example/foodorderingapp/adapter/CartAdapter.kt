package com.example.foodorderingapp.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapp.databinding.CartItemBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartAdapter(
    private val cartItems: MutableList<String>,
    private val cartItemPrice: MutableList<String>,
    private val cartImage: MutableList<String>,
    private val cartDescription: MutableList<String>,
    private val cartQuantity: MutableList<Int>,
    private val cartIngredient: MutableList<String>,
    private val context: Context
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private val auth = FirebaseAuth.getInstance()

    init {
        //initialize firebase
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid
        val cartItemNumber = cartQuantity.size

        itemQuantities = IntArray(cartItemNumber)
        cartItemReference = database.reference
            .child("user").child(userId.toString()).child("CartItems")

    }

    companion object {
        private var itemQuantities: IntArray = intArrayOf()
        private lateinit var cartItemReference: DatabaseReference
    }

    inner class CartViewHolder(val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val quan = itemQuantities[position]
            val cartItems = cartItems[position]
            val cartItemPrice = cartItemPrice[position]
            val imageCart = cartImage[position]
            val cartIngredient = cartIngredient[position]

            binding.tvCartFoodName.text = cartItems
            binding.tvCartPrice.text = cartItemPrice
            binding.tvItemCartQuantities.text = quan.toString()
            // load image using glide
            val uriString = imageCart
            val uri = Uri.parse(uriString)
            Glide.with(context).load(uri).into(binding.imageView)



            binding.minusButton.setOnClickListener {
                if (itemQuantities[position] > 0) {
                    itemQuantities[position]--
                    binding.tvItemCartQuantities.text = itemQuantities[position].toString()

                }

            }
            binding.plusButton.setOnClickListener {
                if (itemQuantities[position] < 10) {
                    itemQuantities[position]++
                    binding.tvItemCartQuantities.text = itemQuantities[position].toString()
                }

            }
            binding.btnTrash.setOnClickListener {

                val adapterPosition = adapterPosition
                deleteItem(adapterPosition)

            }


        }
    }

    private fun deleteItem(adapterPosition: Int) {
        val positionRetrieve = adapterPosition
        getUniqueKeyPosition(positionRetrieve) { uniqueKey ->
            if (uniqueKey != null) {
                removeItem(positionRetrieve, uniqueKey)
            }
        }
    }

    private fun removeItem(positionRetrieve: Int, uniqueKey: String) {
        if (uniqueKey != null) {
            cartItemReference.child(uniqueKey).removeValue().addOnSuccessListener {
                cartItems.removeAt(positionRetrieve)
                cartImage.removeAt(positionRetrieve)
                cartDescription.removeAt(positionRetrieve)
                cartQuantity.removeAt(positionRetrieve)
                cartItemPrice.removeAt(positionRetrieve)
                cartIngredient.removeAt(positionRetrieve)

                //update item quantity
                itemQuantities =
                    itemQuantities.filterIndexed { index, i -> index != positionRetrieve }
                        .toIntArray()
                notifyItemRemoved(positionRetrieve)
                notifyItemRangeChanged(positionRetrieve, cartItems.size)
                Toast.makeText(context, "Falied to delete", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(context, "Falied to delete", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUniqueKeyPosition(positionRetrieve: Int, onComplete: (String?) -> Unit) {
        cartItemReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var uniqueKey: String? = null
                snapshot.children.forEachIndexed { index, dataSnapshot ->
                    if (index == positionRetrieve) {
                        uniqueKey = dataSnapshot.key
                        return@forEachIndexed
                    }
                }
                onComplete(uniqueKey)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        holder.bind(position)


    }

}
package com.example.foodorderingapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentProfileBinding
import com.example.foodorderingapp.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        setUserData()

        binding.btnSaveInfo.setOnClickListener {
            val name = binding.editTextName.toString()
            val phone = binding.editTextPhone.toString()
            val address = binding.editTextAddress.toString()
            val email = binding.editTextEmail.toString()

            updateUserData(name, phone, address, email)
        }
    }

    private fun updateUserData(name: String, phone: String, address: String, email: String) {
        if (userId != null) {
            val userReference = database.getReference("user").child(userId)

            val userData = hashMapOf(
                "name" to name ,
                "address" to address ,
                "email" to email ,
                "phone" to phone
            )
            userReference.setValue(userData).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Update User Data successfull", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Update User Data failed", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun setUserData() {
         userId = auth.currentUser?.uid.toString()
        if (userId != null) {
            val userReference = database.getReference("user").child(userId)
            userReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val userProfile = snapshot.getValue(UserModel::class.java)
                        if (userProfile != null) {
                            binding.editTextName.setText(userProfile.username)
                            binding.editTextAddress.setText(userProfile.address)
                            binding.editTextPhone.setText(userProfile.phone)
                            binding.editTextEmail.setText(userProfile.email)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

}
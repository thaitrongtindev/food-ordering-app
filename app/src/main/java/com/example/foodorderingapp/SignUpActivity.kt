package com.example.foodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.foodorderingapp.databinding.ActivitySignUpBinding
import com.example.foodorderingapp.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var username: String

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        //initialize firebase auth
        auth = Firebase.auth
        // initialize firebase realtime
        database = FirebaseDatabase.getInstance()

        // Already Have an Account
        binding.tvAlreadyHaveAnAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // onclick create account
        binding.btnCreateAccount.setOnClickListener {
            createAccount()
        }

    }

    private fun createAccount() {
        email = binding.editTextEmail.text.toString().trim()
        username = binding.editTextName.text.toString().trim()
        password = binding.edtPassword.text.toString().trim()
        if (email.isBlank() || username.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Please Filled All", Toast.LENGTH_SHORT).show()
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Create Account Success", Toast.LENGTH_SHORT).show()
                    saveUserData()
                    updateUi()
                } else {
                    Toast.makeText(this, "Create Account Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveUserData() {
        val userModel = UserModel(email, password,username)
        val userId = auth.currentUser?.uid
        database.reference.child("user").child(userId.toString()).setValue(userModel)
    }

    private fun updateUi() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }
}
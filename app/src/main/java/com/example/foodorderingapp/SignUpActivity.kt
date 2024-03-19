package com.example.foodorderingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.foodorderingapp.databinding.ActivitySignUpBinding
import com.example.foodorderingapp.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var username: String

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private lateinit var googleSignInClient: GoogleSignInClient


    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                Log.e("task", task.result.toString())
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount = task.result

                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            Toast.makeText(
                                this, "Successfully sign - in with Google", Toast
                                    .LENGTH_SHORT
                            ).show()

                            startActivity(Intent(this , MainActivity::class.java))
                        } else {
                            Toast.makeText(
                                this, "Failed sign - in with Google", Toast
                                    .LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        //initialize firebase auth
        auth = Firebase.auth
        // initialize firebase realtime
        database = FirebaseDatabase.getInstance()

        // init config google login
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("956709816198-mr7hkeei2ohdgrtldhnlub2ca54i36lr.apps.googleusercontent.com")
            .requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)

        // Already Have an Account
        binding.tvAlreadyHaveAnAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // onclick create account
        binding.btnCreateAccount.setOnClickListener {
            createAccount()
        }

        // on click login google
        binding.btnGoogle.setOnClickListener {
            onClickLoginWithGoogle()
        }

    }

    private fun onClickLoginWithGoogle() {
        val signIntent = googleSignInClient.signInIntent
        launcher.launch(signIntent)
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
        val userModel = UserModel(email, password, username)
        val userId = auth.currentUser?.uid
        database.reference.child("user").child(userId.toString()).setValue(userModel)
    }

    private fun updateUi() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }
}
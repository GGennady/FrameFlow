package com.example.frameflow

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.frameflow.databinding.ActivityRegistrationBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class RegistrationActivity : AppCompatActivity() {

    private var _binging: ActivityRegistrationBinding? = null
    private val binding
        get() = _binging ?: throw IllegalStateException("Binding for ActivityRegistration must be not null.")

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res) // startup screen
    }

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binging = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("testLogs", "RegistrationActivity start registration")

        database = Firebase.database.reference // database initialization

        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build() // created intent for firebase auth
        signInLauncher.launch(signInIntent) // firebase auth screen launch
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse // result from firebase auth screen
        if (result.resultCode == RESULT_OK) {
            Log.d("testLogs", "RegistrationActivity registration success ${response?.email}")
            // Successfully signed in
            val authUser = FirebaseAuth.getInstance().currentUser // creating current firebase auth user object
            authUser?.let {
                val email = it.email.toString()
                val uid = it.uid
                val firebaseUser = User(email, uid)
                database.child("users").setValue(firebaseUser)// save our user to Firebase Realtime
            }

            // ...
        } else {
            Log.d("testLogs", "RegistrationActivity registration failed")
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }


}
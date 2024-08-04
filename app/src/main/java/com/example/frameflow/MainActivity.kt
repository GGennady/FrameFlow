package com.example.frameflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.frameflow.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {

    private var _binging: ActivityMainBinding? = null
    private val binding
        get() = _binging ?: throw IllegalStateException("Binding for ActivityMain must be not null.")

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res) // startup screen
    }

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        Log.d("testLogs", "in onCreate")
        // _binging = connection to activity xml
        _binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("testLogs", "RegistrationActivity start registration")

        database = Firebase.database("https://frameflow-77524-default-rtdb.firebaseio.com/").reference // database initialization

        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setIsSmartLockEnabled(false)
            .setAlwaysShowSignInMethodScreen(true)
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
                database.child("users").child(uid).setValue(firebaseUser) // save our user to Firebase Realtime

                val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
                startActivity(intentToAnotherScreen)
            }

        } else {
            Log.d("testLogs", "RegistrationActivity registration failed")
            Toast.makeText(this@MainActivity,"Something wrong with registration", Toast.LENGTH_SHORT).show()
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
}
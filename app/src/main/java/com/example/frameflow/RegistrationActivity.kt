package com.example.frameflow

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.frameflow.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private var _binging: ActivityRegistrationBinding? = null
    private val binding
        get() = _binging ?: throw IllegalStateException("Binding for ActivityRegistration must be not null.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binging = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
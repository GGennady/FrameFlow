package com.example.frameflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.frameflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binging: ActivityMainBinding? = null
    private val binding
        get() = _binging ?: throw IllegalStateException("Binding for ActivityMain must be not null.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test_log", "in onCreate")
        // _binging = connection to activity xml
        _binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityRegistrationButton.setOnClickListener {
            Log.d("test_log2", "Clicked!")
            val intentFromMainToRegistration = Intent(this, RegistrationActivity::class.java)
            startActivity(intentFromMainToRegistration)
            Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}
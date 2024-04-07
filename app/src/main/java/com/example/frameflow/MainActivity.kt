package com.example.frameflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.frameflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding
    private var _binging: ActivityMainBinding? = null
    private val binding
        get() = _binging ?: throw IllegalStateException("Binding for ActivityMain must be not null.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
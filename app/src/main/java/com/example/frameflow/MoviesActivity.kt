package com.example.frameflow

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frameflow.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {

    private var _binging: ActivityMoviesBinding? = null
    private val binding
        get() = _binging ?: throw IllegalStateException("Binding for ActivityMovies must be not null.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binging = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // this creates a vertical layout Manager
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(com.firebase.ui.auth.R.drawable.common_full_open_on_phone, "Item $i"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        binding.recyclerview.adapter = adapter

    }

}
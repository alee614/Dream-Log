package com.example.dreamlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var buttonLog: Button
    private lateinit var recyclerView: RecyclerView

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        buttonLog = findViewById(R.id.button_add)

        val adapter = DreamListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // make function in order to store all the dreams


        buttonLog.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(intent)
        }

        // create observe to observe changes in data within
        dreamViewModel.allDreams.observe(this, androidx.lifecycle.Observer {
            // update the cashed copy of tasks in the adapter to it
                dreams -> dreams?.let {
            adapter.submitList(it)
        }
        })


    }


}
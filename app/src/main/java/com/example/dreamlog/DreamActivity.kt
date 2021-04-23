package com.example.dreamlog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class DreamActivity : AppCompatActivity(){

    private lateinit var textView_dreamTitle: TextView
    private lateinit var textView_content: TextView
    private lateinit var textView_interpretation: TextView
    private lateinit var textView_emotion:TextView

    private lateinit var button_delete: Button
    private lateinit var button_update: Button

    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dream)
        val id = intent.getIntExtra("id", 0);

        textView_dreamTitle = findViewById(R.id.textView_dreamTitle)
        textView_content = findViewById(R.id.textView_content)
        textView_interpretation = findViewById(R.id.textView_interpretation)
        textView_emotion = findViewById(R.id.textView_emotion)

        button_delete = findViewById(R.id.button_delete)
        button_update = findViewById(R.id.button_update)

        //  condition to check
        dreamViewModel.find(id).observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                val title = it.title
                val content = it.content
                val interpretation = it.reflection
                val emotion = it.emotion

                textView_dreamTitle.setText(title)
                textView_content.setText(content)
                textView_interpretation.setText(interpretation)
                textView_emotion.setText(emotion)
            }

        })


        button_update.setOnClickListener {
            // intent with the id sent
            val intent = Intent(this, AddActivity::class.java).apply {
                putExtra("id", id)
            }
            startActivity(intent)
        }
        button_delete.setOnClickListener {
            //Log.e("deleted id", id.toString())
            dreamViewModel.delete(id)
            finish()
        }

    }
}
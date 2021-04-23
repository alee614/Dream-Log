package com.example.dreamlog

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class AddActivity : AppCompatActivity(){

    private lateinit var editText_title:EditText
    private lateinit var editText_content:EditText
    private lateinit var editText_interpretation:EditText
    private lateinit var button:Button
    private lateinit var spinner: Spinner

    // this variable will change if there is an intent with a value to be true
    // then the save button will invoke the update function with the id and the collected info
    // rather than insert
    private var update:Boolean = false


    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        Log.e("intent", intent.toString())


        editText_title = findViewById(R.id.editText_title)
        editText_content = findViewById(R.id.editText_content)
        editText_interpretation = findViewById(R.id.editText_interpretation)
        button = findViewById(R.id.button)
        spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.emotions_list,
            android.R.layout.simple_spinner_item
        ).also {
            adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val res = resources.getStringArray(R.array.emotions_list)


        val check = getIntent().hasExtra("id")
        val id = getIntent().getIntExtra("id", 0)
        if (check){
            Log.e("booelan", " is trues")
            update = true
            dreamViewModel.find(id).observe(this, Observer {
                val title = it.title
                val content = it.content
                val interpretation = it.reflection
                val emotion = it.emotion
                Log.e("title", title)
                editText_title.setText(title)
                editText_content.setText(content)
                editText_interpretation.setText(interpretation)

                var i = 0
                for (listedEmotion in res){
                    if (emotion.equals(listedEmotion)){
                        spinner.setSelection(i)
                    }
                    else{
                        i++
                    }
                }
            })

            // runs through the emotions_list
            // if the emotion matches editText_emotion, set the spinner.setSelection(int)


        }




        button.setOnClickListener {
            val emotion = spinner.selectedItem

            if (TextUtils.isEmpty(editText_title.text)){
                toastError("Missing field in title")
            }
            else if (TextUtils.isEmpty(editText_content.text)){
                toastError("You didn't tell us what happened in your dream.")
            }
            else if(TextUtils.isEmpty(editText_interpretation.text)){
                toastError("You should consider what this dream means.")
            }
            else{

                if (update){
                    Log.e("update at", id.toString())
                    dreamViewModel.update(editText_title.text.toString(), editText_content.text.toString(),
                    editText_interpretation.text.toString(), emotion.toString(), id)
                    update = false

                }
                else{
                    Log.e("insert", "new dream")
                    val dream = Dream(editText_title.text.toString(),editText_content.text.toString(), editText_interpretation.text.toString(), emotion.toString())
                    dreamViewModel.insert(dream)
                }

                // if the id doesnt exist them we insert
                // if the id does exist then we update

                finish()
            }

        }

    }

    private fun toastError(text:String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}


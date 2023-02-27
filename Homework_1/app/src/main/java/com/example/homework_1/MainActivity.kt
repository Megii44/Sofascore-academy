package com.example.homework_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var helloWorldText: TextView
    private lateinit var toggleButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloWorldText = findViewById(R.id.hello_world_text)
        toggleButton = findViewById(R.id.toggle_button)

        toggleButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view == toggleButton) {
            if (helloWorldText.visibility == View.VISIBLE) {
                helloWorldText.visibility = View.INVISIBLE
                toggleButton.text = "Display Text"
            } else {
                helloWorldText.visibility = View.VISIBLE
                toggleButton.text = "Hide Text"
            }
        }
    }
}
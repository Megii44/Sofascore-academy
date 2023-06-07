package com.example.minisofascore.ui.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.minisofascore.databinding.ActivityPlayerDetailsBinding

class PlayerDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerDetailsBinding.inflate(layoutInflater)

        // Use the binding object to access views and set data
    }
}

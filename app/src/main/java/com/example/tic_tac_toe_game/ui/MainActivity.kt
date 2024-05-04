package com.example.tic_tac_toe_game.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tic_tac_toe_game.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickListener()


    }

    private fun onClickListener() {
        binding.HomeMultiplayer.setOnClickListener {
            startActivity(Intent(this, Multiplayer::class.java))
        }
    }


}
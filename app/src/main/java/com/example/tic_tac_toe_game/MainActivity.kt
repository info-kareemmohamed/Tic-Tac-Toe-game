package com.example.tic_tac_toe_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tic_tac_toe_game.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var intent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickListener()


    }

    private fun onClickListener() {
        binding.MultiplePlayersButtonStart.setOnClickListener {
            intent = Intent(this, TicTacToe::class.java)
            intent.putExtra("nameOne",binding.MultiplePlayersNamePlayerOne.text.toString())
            intent.putExtra("nameTwo",binding.MultiplePlayersNamePlayerTwo.text.toString())
            startActivity(intent)
        }
    }


}
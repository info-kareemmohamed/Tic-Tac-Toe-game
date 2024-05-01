package com.example.tic_tac_toe_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tic_tac_toe_game.databinding.ActivityTicTacToeBinding

class TicTacToe : AppCompatActivity() {
    private lateinit var binding: ActivityTicTacToeBinding
    private lateinit var intent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent = getIntent()
        binding.TicTacNameO.text = intent.getStringExtra("nameTwo")
        binding.TicTacNameX.text = intent.getStringExtra("nameOne")

    }
}
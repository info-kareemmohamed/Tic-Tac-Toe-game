package com.example.tic_tac_toe_game.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tic_tac_toe_game.R
import com.example.tic_tac_toe_game.databinding.ActivityMultiplayerBinding

class Multiplayer : AppCompatActivity() {
    private lateinit var binding:ActivityMultiplayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMultiplayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickListener()
    }



    private fun onClickListener() {
        binding.MultiplePlayersButtonStart.setOnClickListener {
            intent = Intent(this, TicTacToe::class.java)
            intent.putExtra("nameOne",binding.MultiplePlayersNamePlayerOne.text.toString())
            intent.putExtra("nameTwo",binding.MultiplePlayersNamePlayerTwo.text.toString())
            intent.putExtra("numberOfCycles",binding.MultiplePlayersNumberOfCycles.text.toString())
            startActivity(intent)
        }
    }
}
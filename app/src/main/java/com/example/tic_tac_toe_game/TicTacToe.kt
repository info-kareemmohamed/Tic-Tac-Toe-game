package com.example.tic_tac_toe_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.tic_tac_toe_game.databinding.ActivityTicTacToeBinding

class TicTacToe : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTicTacToeBinding
    private lateinit var intent: Intent
    private var gameModel: GameModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent = getIntent()
        binding.TicTacNameO.text = intent.getStringExtra("nameTwo")
        binding.TicTacNameX.text = intent.getStringExtra("nameOne")

        setOnClick()
        startGame()
    }

    private fun setOnClick() {
        binding.TicTacImage1.setOnClickListener(this)
        binding.TicTacImage2.setOnClickListener(this)
        binding.TicTacImage3.setOnClickListener(this)
        binding.TicTacImage4.setOnClickListener(this)
        binding.TicTacImage5.setOnClickListener(this)
        binding.TicTacImage6.setOnClickListener(this)
        binding.TicTacImage7.setOnClickListener(this)
        binding.TicTacImage8.setOnClickListener(this)
        binding.TicTacImage9.setOnClickListener(this)
    }

    private fun startGame() {
        gameModel = GameModel()
        val childCount = binding.TicTacGridLayout.childCount

        for (i in 0 until childCount) {
            val imageView = binding.TicTacGridLayout.getChildAt(i) as ImageView

            imageView.setImageResource(R.drawable.round_back_dark)

        }

        gameModel?.gameStatus = GameStatus.INPROGRESS
        updateGameData(gameModel!!)
    }

    private fun updateGameData(model: GameModel) {
        GameData.saveGameModel(model)

    }

    private fun cheackForWinner() {
        val winningPosition = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6),
        )
        gameModel?.apply {
            for (i in winningPosition) {
                if (filledPosition[i[0]] == filledPosition[i[1]] && filledPosition[i[1]] == filledPosition[i[2]] && filledPosition[i[0]].isNotEmpty()) {
                    gameStatus = GameStatus.FINISHED
                    winner = filledPosition[i[0]]
                    Toast.makeText(applicationContext, winner, Toast.LENGTH_LONG).show()

                }
            }
            if (filledPosition.none() { it.isEmpty() }) {
                gameStatus = GameStatus.FINISHED
            }
            updateGameData(this)
        }

    }

    override fun onClick(v: View?) {

        gameModel?.apply {

            if (gameStatus != GameStatus.INPROGRESS) {
                Toast.makeText(applicationContext, "Game not Started", Toast.LENGTH_SHORT).show()
                return
            }else if(gameStatus==GameStatus.FINISHED)startGame()


            var position: Int = (v?.tag.toString()).toInt() - 1


            if (filledPosition[position].isEmpty()) {

                if (currentPlayer) {
                    (v as ImageView).setImageResource(R.drawable.close)
                    filledPosition[position] = "X"
                    currentPlayer = false;
                } else {

                    (v as ImageView).setImageResource(R.drawable.zero)
                    filledPosition[position] = "O"
                    currentPlayer = true;
                }
                updateGameData(this)
                cheackForWinner()
            }

        }


    }
}
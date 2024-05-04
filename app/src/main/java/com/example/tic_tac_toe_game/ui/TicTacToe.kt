package com.example.tic_tac_toe_game.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.tic_tac_toe_game.CustomDialog
import com.example.tic_tac_toe_game.CustomDialogClickListener
import com.example.tic_tac_toe_game.GameData
import com.example.tic_tac_toe_game.GameModel
import com.example.tic_tac_toe_game.GameStatus
import com.example.tic_tac_toe_game.R
import com.example.tic_tac_toe_game.databinding.ActivityTicTacToeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TicTacToe : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityTicTacToeBinding
    private lateinit var intent: Intent
    private var gameModel: GameModel = GameModel()
    private var numberOfCycles = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent = getIntent()
        binding.TicTacNameO.text = intent.getStringExtra("nameTwo")
        binding.TicTacNameX.text = intent.getStringExtra("nameOne")
        numberOfCycles = intent.getStringExtra("numberOfCycles")!!.toInt()
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


        gameModel.apply {

            updateGameData(
                GameModel(
                    scoreO = scoreO,
                    scoreX = scoreX,
                    gameStatus = GameStatus.INPROGRESS

                )

            )

        }
        drawScreen()
    }

    private fun drawScreen() {

        val childCount = binding.TicTacGridLayout.childCount

        if (gameModel.currentPlayer.equals("X")) {
            binding.TicTacLinearLayoutO.background =
                ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_dark)
            binding.TicTacLinearLayoutX.background =
                ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_border)

        } else {
            binding.TicTacLinearLayoutX.background =
                ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_dark)
            binding.TicTacLinearLayoutO.background =
                ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_border)

        }
        binding.TicTacScoreX.text = gameModel.scoreX.toString()
        binding.TicTacScoreO.text = gameModel.scoreO.toString()
        for (i in 0 until childCount) {
            val imageView = binding.TicTacGridLayout.getChildAt(i) as ImageView

            imageView.setImageResource(0)
            imageView.background =
                ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_dark)
        }


    }

    private fun updateGameData(model: GameModel) {
        GameData.saveGameModel(model)
        gameModel = model

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



        gameModel.apply {
            for (i in winningPosition) {
                if (filledPosition[i[0]].isNotEmpty() && filledPosition[i[0]] == filledPosition[i[1]] && filledPosition[i[1]] == filledPosition[i[2]]) {

                    winner =
                        if (filledPosition[i[0]] == "X") {
                            scoreX += 1
                            binding.TicTacNameX.text.toString()

                        } else {
                            scoreO += 1
                            binding.TicTacNameO.text.toString()
                        }

                    (binding.TicTacGridLayout.getChildAt(i[0]) as ImageView).background =
                        ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_border)
                    (binding.TicTacGridLayout.getChildAt(i[1]) as ImageView).background =
                        ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_border)
                    (binding.TicTacGridLayout.getChildAt(i[2]) as ImageView).background =
                        ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_border)
                    gameStatus = GameStatus.FINISHED


                }
            }
            if (filledPosition.none() { it.isEmpty() }) {
                gameStatus = GameStatus.FINISHED
            }
            binding.TicTacScoreX.text = scoreX.toString()
            binding.TicTacScoreO.text = scoreO.toString()
            updateGameData(this)

        }

    }


    override fun onClick(v: View?) {

        gameModel.apply {

            var position: Int = (v?.tag.toString()).toInt() - 1


            if (filledPosition[position].isEmpty() && gameStatus != GameStatus.FINISHED) {
                if (currentPlayer.equals("X")) {
                    (v as ImageView).setImageResource(R.drawable.close)
                    filledPosition[position] = currentPlayer
                    binding.TicTacLinearLayoutX.background =
                        ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_dark)
                    binding.TicTacLinearLayoutO.background =
                        ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_border)

                    currentPlayer = "O";
                } else {

                    (v as ImageView).setImageResource(R.drawable.zero)
                    filledPosition[position] = currentPlayer
                    binding.TicTacLinearLayoutO.background =
                        ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_dark)
                    binding.TicTacLinearLayoutX.background =
                        ContextCompat.getDrawable(this@TicTacToe, R.drawable.round_back_border)

                    currentPlayer = "X";
                }
                updateGameData(this)
                cheackForWinner()
            }
            if (gameStatus == GameStatus.FINISHED) {
                CoroutineScope(Dispatchers.Main).launch {

                    delay(1000)
                    if (numberOfCycles == maxOf(scoreO, scoreX)) CustomDialog(
                        this@TicTacToe,
                        "The Winner is ${winner}",
                        "Start new Cycles", object : CustomDialogClickListener {
                            override fun onButtonClick() {
                                scoreX = 0
                                scoreO = 0
                                startGame()
                            }
                        }
                    ).show()
                    else
                        startGame()
                }

            }

        }


    }


}
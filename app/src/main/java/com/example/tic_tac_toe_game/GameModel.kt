package com.example.tic_tac_toe_game

import kotlin.random.Random

data class GameModel(
    var gameId:String = "-1",
    var filledPosition:MutableList<String> = mutableListOf("","","","","","","","",""),
    var winner:String="",
    var scoreX:Int=0,
    var scoreO:Int=0,
    var gameStatus: GameStatus =GameStatus.CREATED,
    var currentPlayer:String= (arrayOf("X","O"))[Random.nextInt(2)],

)
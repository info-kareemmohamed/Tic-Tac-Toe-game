package com.example.tic_tac_toe_game

data class GameModel(
    var gameId:String = "-1",
    var filledPosition:MutableList<String> = mutableListOf("","","","","","","","",""),
    var winner:String="",
    var gameStatus: GameStatus =GameStatus.CREATED,
    var currentPlayer:Boolean=true,//true=X false=O

)
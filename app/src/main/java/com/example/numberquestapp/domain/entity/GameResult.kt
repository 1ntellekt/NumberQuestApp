package com.example.numberquestapp.domain.entity

data class GameResult(
    val winner:Boolean,
    val countOfRightAnswers:Int,
    val countOfQuestions:Int,
    val gameSettings: GameSettings
)

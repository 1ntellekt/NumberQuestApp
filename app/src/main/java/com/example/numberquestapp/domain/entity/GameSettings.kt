package com.example.numberquestapp.domain.entity

data class GameSettings(
    val maxSumVal:Int,
    val minCountOfRightAnswers:Int,
    val minPercentOfRightAnswers:Int,
    val gameTimeInSeconds:Int
)
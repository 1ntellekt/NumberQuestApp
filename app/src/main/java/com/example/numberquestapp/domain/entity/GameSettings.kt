package com.example.numberquestapp.domain.entity

import java.io.Serializable

data class GameSettings(
    val maxSumVal:Int,
    val minCountOfRightAnswers:Int,
    val minPercentOfRightAnswers:Int,
    val gameTimeInSeconds:Int
):Serializable
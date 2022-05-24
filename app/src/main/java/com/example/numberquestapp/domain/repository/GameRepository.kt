package com.example.numberquestapp.domain.repository

import com.example.numberquestapp.domain.entity.GameSettings
import com.example.numberquestapp.domain.entity.Level
import com.example.numberquestapp.domain.entity.Question

interface GameRepository {

    fun generateQuestion(maxSumVal:Int, countOfOptions:Int):Question

    fun getGameSettings(level: Level):GameSettings
}
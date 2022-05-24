package com.example.numberquestapp.data

import com.example.numberquestapp.domain.entity.GameSettings
import com.example.numberquestapp.domain.entity.Level
import com.example.numberquestapp.domain.entity.Question
import com.example.numberquestapp.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class GameRepositoryImpl : GameRepository {

    override fun generateQuestion(maxSumVal: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VAL,maxSumVal+1)
        val visibleNumber =  Random.nextInt(MIN_ANSWER_VAL,sum)
        val options = hashSetOf<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer-countOfOptions, MIN_ANSWER_VAL)
        val to = min(maxSumVal ,rightAnswer+countOfOptions)
        while (options.size < countOfOptions){
            options.add(Random.nextInt(from,to))
        }
        return Question(sum,visibleNumber,options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> GameSettings(10,3,50,8)
            Level.EASY -> GameSettings(10,10,70,60)
            Level.NORMAL -> GameSettings(20,20,80,40)
            Level.HARD -> GameSettings(30,30,90,30)
        }
    }

    private companion object {
        private const val MIN_SUM_VAL = 2
        private const val MIN_ANSWER_VAL = 1
    }

}
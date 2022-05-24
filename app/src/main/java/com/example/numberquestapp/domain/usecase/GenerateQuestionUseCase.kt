package com.example.numberquestapp.domain.usecase


import com.example.numberquestapp.domain.entity.Question
import com.example.numberquestapp.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumVal:Int):Question {
        return repository.generateQuestion(maxSumVal, COUNT_OF_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }

}
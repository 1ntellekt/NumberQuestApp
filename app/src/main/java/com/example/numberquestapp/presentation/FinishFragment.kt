package com.example.numberquestapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.example.numberquestapp.R
import com.example.numberquestapp.databinding.FragmentFinishBinding
import com.example.numberquestapp.domain.entity.GameResult
import java.lang.RuntimeException

class FinishFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentFinishBinding? = null
    private val binding: FragmentFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentFinishBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }
        })
        binding.apply {
            btnRetry.setOnClickListener {
                retryGame()
            }
            if (gameResult.winner){
                emojiResult.setImageResource(R.drawable.ic_smile)
            } else {
                emojiResult.setImageResource(R.drawable.ic_sad)
            }
            tvRequiredAnswers.text = String.format(
                resources.getString(R.string.required_score),
                gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                resources.getString(R.string.score_answers),
                gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                resources.getString(R.string.required_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                resources.getString(R.string.required_percentage),
                getPercent()
            )
        }
    }

    private fun getPercent():Int = with(gameResult){
        if (countOfQuestions == 0) return 0
        return (countOfRightAnswers/countOfQuestions.toDouble() * 100).toInt()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs(){
        requireArguments().getParcelable<GameResult>(GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun retryGame(){
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME,FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    companion object {
        const val GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult):FinishFragment{
            return FinishFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT,gameResult)
                }
            }
        }
    }

}
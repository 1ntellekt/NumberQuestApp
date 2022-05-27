package com.example.numberquestapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.numberquestapp.R
import com.example.numberquestapp.databinding.FragmentFinishBinding
import com.example.numberquestapp.domain.entity.GameResult
import java.lang.RuntimeException

class FinishFragment : Fragment() {

    private val args by navArgs<FinishFragmentArgs>()

    private var _binding: FragmentFinishBinding? = null
    private val binding: FragmentFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentFinishBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnRetry.setOnClickListener {
                retryGame()
            }
            if (args.result.winner){
                emojiResult.setImageResource(R.drawable.ic_smile)
            } else {
                emojiResult.setImageResource(R.drawable.ic_sad)
            }
            tvRequiredAnswers.text = String.format(
                resources.getString(R.string.required_score),
                args.result.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                resources.getString(R.string.score_answers),
                args.result.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                resources.getString(R.string.required_percentage),
                args.result.gameSettings.minPercentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                resources.getString(R.string.required_percentage),
                getPercent()
            )
        }
    }

    private fun getPercent():Int = with(args.result){
        if (countOfQuestions == 0) return 0
        return (countOfRightAnswers/countOfQuestions.toDouble() * 100).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame(){
        findNavController().popBackStack()
    }

/*    companion object {
        const val GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult):FinishFragment{
            return FinishFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT,gameResult)
                }
            }
        }
    }*/

}
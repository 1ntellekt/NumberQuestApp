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
        binding.gameResult = args.result
        binding.apply {
            btnRetry.setOnClickListener {
                retryGame()
            }
        }
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
package com.example.numberquestapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numberquestapp.R
import com.example.numberquestapp.databinding.FragmentGameBinding
import com.example.numberquestapp.domain.entity.GameResult
import com.example.numberquestapp.domain.entity.GameSettings
import com.example.numberquestapp.domain.entity.Level
import java.lang.RuntimeException

class GameFragment : Fragment() {

    private lateinit var level: Level

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvLeftNumber.setOnClickListener {
                launchFinishFragment(GameResult(true,0,0, GameSettings(0,0,0,0)))
            }
        }
    }

    private fun launchFinishFragment(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,FinishFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs(){
        level = requireArguments().getSerializable(KEY_LEVEL) as Level
    }

    companion object {
        const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level):GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL,level)
                }
            }
        }
    }

}
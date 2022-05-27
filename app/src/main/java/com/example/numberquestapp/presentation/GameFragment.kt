package com.example.numberquestapp.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.numberquestapp.R
import com.example.numberquestapp.databinding.FragmentGameBinding
import com.example.numberquestapp.domain.entity.GameResult
import com.example.numberquestapp.domain.entity.GameSettings
import com.example.numberquestapp.domain.entity.Level
import java.lang.RuntimeException

class GameFragment : Fragment() {

    private val viewModelFactory by lazy {
        GameViewModelFactory(requireActivity().application,level)
    }

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

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
        observeViewModel()
        setClickListenersToOptions()
    }

    private fun setClickListenersToOptions(){
        for (tv in tvOptions){
            tv.setOnClickListener {
                viewModel.chooseAnswer(tv.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel() {
        binding.apply {
            viewModel.question.observe(viewLifecycleOwner) {
                tvLeftNumber.text = it.visibleNumber.toString()
                tvSum.text = it.sum.toString()
                for (i in 0 until tvOptions.size){
                    tvOptions[i].text = it.options[i].toString()
                }
            }
            viewModel.percentOfRightQuestions.observe(viewLifecycleOwner){
                progressBar.setProgress(it,true)
            }
            viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner){
                if (it){
                    tvAnswersProgress.setTextColor(Color.RED)
                }else{
                    tvAnswersProgress.setTextColor(Color.GREEN)
                }
            }
            viewModel.progressAnswers.observe(viewLifecycleOwner){
                tvAnswersProgress.text = it
            }
            viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner){
                if (it){
                    progressBar.progressTintList = ColorStateList.valueOf(Color.GREEN)
                }else{
                    progressBar.progressTintList = ColorStateList.valueOf(Color.RED)
                }
            }
            viewModel.formattedTime.observe(viewLifecycleOwner) {
                tvTimer.text = it
            }
            viewModel.minPercent.observe(viewLifecycleOwner){
                progressBar.secondaryProgress = it
            }
            viewModel.gameResult.observe(viewLifecycleOwner) {
                launchFinishFragment(it)
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
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    companion object {
        const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level):GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL,level)
                }
            }
        }
    }

}
package com.example.numberquestapp.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.numberquestapp.R
import com.example.numberquestapp.domain.entity.GameResult


interface OnOptionClickListener{
    fun onOptionClick(option:Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView,count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView,percent:Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percent
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult){
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercent(gameResult)
    )
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner:Boolean) {
    if (winner){
        imageView.setImageResource(R.drawable.ic_smile)
    } else {
        imageView.setImageResource(R.drawable.ic_sad)
    }
}

@BindingAdapter("progressBar")
fun bindProgressBar(progressBar: ProgressBar, percent: Int){
    progressBar.setProgress(percent,true)
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough:Boolean){
    if (enough){
        progressBar.progressTintList = ColorStateList.valueOf(Color.GREEN)
    }else{
        progressBar.progressTintList = ColorStateList.valueOf(Color.RED)
    }
}

@BindingAdapter("enoughCount")
fun bindEnoughCountOfRightAnswers(textView: TextView, enough: Boolean){
    if (enough){
        textView.setTextColor(Color.GREEN)
    }else{
        textView.setTextColor(Color.RED)
    }
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number:Int){
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener:OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

private fun getPercent(gameResult: GameResult): Int = with(gameResult){
        if (countOfQuestions == 0) return 0
        return (countOfRightAnswers/countOfQuestions.toDouble() * 100).toInt()
    }


package com.udaychugh.quizgame

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.udaychugh.quizgame.extra.Question
import com.udaychugh.quizgame.extra.Constants

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    lateinit var optionOne : TextView
    lateinit var optionTwo : TextView
    lateinit var optionThree : TextView
    lateinit var optionFour : TextView
    lateinit var tv_progress : TextView
    lateinit var tv_question : TextView
    lateinit var btnSub : Button
    lateinit var progressBar : ProgressBar


    val counterTv by lazy {
        findViewById<TextView>(R.id.counterTv)
    }

    private lateinit var mTimer : CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        optionOne=findViewById(R.id.tv_option_one)
        optionTwo=findViewById(R.id.tv_option_two)
        optionThree=findViewById(R.id.tv_option_three)
        optionFour=findViewById(R.id.tv_option_four)
        tv_progress=findViewById(R.id.tv_progress)
        tv_question=findViewById(R.id.tv_question)
        btnSub=findViewById(R.id.btn_submit)
        progressBar=findViewById(R.id.progressBar)

        mQuestionList = Constants.getQuestions()
        setQuestion()

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        btnSub.setOnClickListener(this)

    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val question = mQuestionList!!.get(mCurrentPosition - 1)
        startCounter(300000)
        defaultOptionsView()
        if (mCurrentPosition == mQuestionList!!.size) {
            btnSub.text = "Finish"
        } else {
            btnSub.text = "Submit"
        }

        progressBar.progress = mCurrentPosition

        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_question.text = question.question
        optionOne.text = question.One
        optionTwo.text = question.Two
        optionThree.text = question.Three
        optionFour.text = question.Four
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, optionOne)
        options.add(1, optionTwo)
        options.add(2, optionThree)
        options.add(3, optionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(optionOne, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(optionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(optionThree, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(optionFour, 4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(
                                this,
                                "You have successfully completed the Quiz", Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctOption != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.correctOption, R.drawable.correct_option_border_bg)
                    if (mCurrentPosition == mQuestionList!!.size) {
                        btnSub.text = "Finish"
                    } else {
                        btnSub.text = "Go to next question"
                    }
                    mSelectedOptionPosition = 0
                }

            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                optionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                optionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                optionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                optionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }


    private fun startCounter(time: Long) {
        counterTv.isVisible = true
        mTimer = object : CountDownTimer(time, 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(timeLeft: Long) {
                counterTv.text = "Time Remaining : " + timeLeft/1000 + " seconds"
            }

            override fun onFinish() {
                counterTv.isVisible = false
            }
        }.start()
    }

}
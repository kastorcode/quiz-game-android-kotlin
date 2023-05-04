package com.kastorcode.quizgame.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.kastorcode.quizgame.R
import com.kastorcode.quizgame.services.quiz.Questions


class MainActivity : AppCompatActivity() {

    private lateinit var score : TextView
    private lateinit var questions : TextView
    private lateinit var answers : Array<Button>
    private lateinit var questionsModel : Questions
    private var userScore = 0
    private var currentQuestion = 0


    override fun onCreate (savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setProps()
        setGuiComponents()
        setClickListeners()
    }


    private fun setProps () {
        score = findViewById(R.id.score)
        questions = findViewById(R.id.questions)
        answers = arrayOf(
            findViewById(R.id.answer1),
            findViewById(R.id.answer2),
            findViewById(R.id.answer3),
            findViewById(R.id.answer4)
        )
        questionsModel = Questions()
        userScore = 0
        currentQuestion = 0
    }


    @SuppressLint("SetTextI18n")
    private fun setGuiComponents () {
        val question = questionsModel.getQuestion(currentQuestion)
        if (question == null) {
            gameOver()
            return
        }
        val choices = questionsModel.getChoices()
        score.text = getString(R.string.score) + ": $userScore"
        questions.text = question
        for (i in answers.indices) {
            answers[i].text = choices[i]
        }
    }


    private fun setClickListeners () {
        for (i in answers.indices) {
            answers[i].setOnClickListener {
                checkAnswer(i)
            }
        }
        val toolbarTitle : LinearLayout = findViewById(R.id.toolbar_title)
        toolbarTitle.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage(getString(R.string.poweredBy) + "\ngithub.com/kastorcode")
                .setCancelable(true)
                .setNeutralButton(R.string.close) { dialogInterface, _ -> dialogInterface.dismiss() }
                .create()
                .show()
        }
    }


    private fun checkAnswer (index : Int) {
        if (answers[index].text == questionsModel.getCorrectAnswer(currentQuestion)) {
            userScore++
        }
        currentQuestion++
        setGuiComponents()
    }


    private fun gameOver () {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage(getString(R.string.gameOverMessage).replace("\$0", "$userScore"))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.newGame).uppercase()) { _, _ ->
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
            .setNegativeButton(getString(R.string.exit).uppercase()) { _, _ ->
                finish()
            }
            .create()
            .show()
    }

}
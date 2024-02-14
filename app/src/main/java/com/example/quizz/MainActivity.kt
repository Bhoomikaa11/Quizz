package com.example.thequiz

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.quizz.R
import com.example.quizz.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var questions = arrayOf(
        "What is the keyword used to define a class in Kotlin?",
        "Which of the following is not a valid way to create an object in Kotlin?",
        "What is the keyword used to define a singleton class in Kotlin?",
        "What is the keyword used to define an interface in Kotlin?",
        "What is the keyword used to define an extension function in Kotlin?",
        "What is the keyword used to define a type alias in Kotlin?",
        "What is the keyword used to define a companion object in Kotlin?",
        "What is the keyword used to define a constructor in Kotlin?",
        "What is the syntax for calling a method in Kotlin?",
        "What is the keyword used to define a destructor in Kotlin?")

    private var option = arrayOf(
        arrayOf("class", "struct", "object", "Type"),
        arrayOf("Using the keyword new", "Using the keyword instance", "Using the keyword val", "Using the keyword object"),
        arrayOf("singleton", "static", "object", "final"),
        arrayOf("class", "interface", "object", "abstract"),
        arrayOf("extend", "operator", "fun","init" ),
        arrayOf("alias", "define", "type", "typedef"),
        arrayOf("class", "companion", "object", "static"),
        arrayOf("constructor", "new", "create", "init"),
        arrayOf("object.method()", "object.function()", "object.call()", "object.procedure()"),
        arrayOf("destructor", "init", "finalize", "dispose"))

    private var correctanswer = arrayOf(0, 1, 2, 1, 2, 2, 1, 3, 0, 2)
    private var currentqueindex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayedquestion()
        binding.op1.setOnClickListener {
            checkAnswer(0)
        }
        binding.op2.setOnClickListener {
            checkAnswer(1)
        }
        binding.op3.setOnClickListener {
            checkAnswer(2)
        }
        binding.op4.setOnClickListener {
            checkAnswer(3)
        }
        binding.restart.setOnClickListener {
            restart()
        }
    }

    private fun correctuttoncolor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.op1.setBackgroundColor(Color.GREEN)
            1 -> binding.op2.setBackgroundColor(Color.GREEN)
            2 -> binding.op3.setBackgroundColor(Color.GREEN)
            3 -> binding.op4.setBackgroundColor(Color.GREEN)
        }
    }

    private fun incorrectuttoncolor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.op1.setBackgroundColor(Color.RED)
            1 -> binding.op2.setBackgroundColor(Color.RED)
            2 -> binding.op3.setBackgroundColor(Color.RED)
            3 -> binding.op4.setBackgroundColor(Color.RED)
        }
    }

    private fun resetcolor() {
        binding.op1.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.op2.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.op3.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.op4.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    private fun result() {

        val hum = AlertDialog.Builder(this)
        if (score < 4) {
            hum.setTitle("RESULT")
            hum.setMessage("Failed !! Your Score is $score out of ${questions.size}")
            hum.setIcon(R.drawable.fail)
            hum.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                onStop()
            })
            hum.show()
            binding.restart.isEnabled = true
        } else if (score > 5  ) {
            hum.setTitle("RESULT")
            hum.setMessage("Congratulations , Passed !!\n Your Score is  $score out of ${questions.size}")
            hum.setIcon(R.drawable.winner)
            hum.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                onStop()
            })
            hum.show()
            binding.restart.isEnabled = true
        }

    }

    private fun displayedquestion() {
        binding.question.text = questions[currentqueindex]
        binding.op1.text = option[currentqueindex][0]
        binding.op2.text = option[currentqueindex][1]
        binding.op3.text = option[currentqueindex][2]
        binding.op4.text = option[currentqueindex][3]
        resetcolor()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctanswerIndex = correctanswer[currentqueindex]

        if (selectedAnswerIndex == correctanswerIndex) {
            score++
            correctuttoncolor(selectedAnswerIndex)
        } else {
            incorrectuttoncolor(selectedAnswerIndex)
            correctuttoncolor(correctanswerIndex)
        }
        if (currentqueindex < questions.size - 1) {
            currentqueindex++;
            binding.question.postDelayed({ displayedquestion() }, 1000)
        } else {
            result()
        }
    }

    private fun restart() {
        currentqueindex = 0
        score = 0
        displayedquestion()
        binding.restart.isEnabled = false
    }
}
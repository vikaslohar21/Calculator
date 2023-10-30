package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var currentInput = StringBuilder()
    private lateinit var display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.textView)
    }

    fun onDigitButtonClick(view: View) {
        val button = view as Button
        currentInput.append(button.text.toString())
        display.text = currentInput.toString()
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        currentInput.append(button.text.toString())
        display.text = currentInput.toString()
    }

    fun onEqualClick(view: View) {
        try {
            val result = evaluateExpression(currentInput.toString())
            display.text = result.toString()
            currentInput.setLength(0)
            currentInput.append(result)
        } catch (e: Exception) {
            display.text = "Error"
            currentInput.setLength(0)
        }
    }

    fun onClearClick(view: View) {
        currentInput.setLength(0)
        currentInput.setLength(0)
        display.text = "0"

    }

    fun onBackspaceClick(view: View) {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            display.text= currentInput.toString()
        }
    }

    private fun evaluateExpression(expression: String): Double {
        try {
            val exp = ExpressionBuilder(expression).build()
            return exp.evaluate()
        } catch (e: ArithmeticException) {
            throw ArithmeticException("Invalid Expression")
        }
    }

}
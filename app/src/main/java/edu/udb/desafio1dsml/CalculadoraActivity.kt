package edu.udb.desafio1dsml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var etDisplay: EditText
    private var operand1: Float? = null
    private var operand2: Float? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        etDisplay = findViewById(R.id.etDisplay)

        val buttonClickListener = View.OnClickListener { view ->
            val button = view as Button
            etDisplay.append(button.text)
        }

        findViewById<Button>(R.id.btn0).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btn1).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btn2).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btn3).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btn4).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btn5).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btn6).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btn7).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btn8).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btn9).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.btnComma).setOnClickListener(buttonClickListener)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            operator = "+"
            operand1 = etDisplay.text.toString().toFloatOrNull()
            etDisplay.text.clear()
        }

        findViewById<Button>(R.id.btnSubtract).setOnClickListener {
            operator = "-"
            operand1 = etDisplay.text.toString().toFloatOrNull()
            etDisplay.text.clear()
        }

        findViewById<Button>(R.id.btnMultiply).setOnClickListener {
            operator = "*"
            operand1 = etDisplay.text.toString().toFloatOrNull()
            etDisplay.text.clear()
        }

        findViewById<Button>(R.id.btnDivide).setOnClickListener {
            operator = "/"
            operand1 = etDisplay.text.toString().toFloatOrNull()
            etDisplay.text.clear()
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            operand2 = etDisplay.text.toString().toFloatOrNull()
            val result = when (operator) {
                "+" -> operand1!! + operand2!!
                "-" -> operand1!! - operand2!!
                "*" -> operand1!! * operand2!!
                "/" -> if (operand2 != 0f) operand1!! / operand2!! else "Error: División por cero"
                else -> "Operación inválida"
            }
            etDisplay.setText(result.toString())
        }
    }
}

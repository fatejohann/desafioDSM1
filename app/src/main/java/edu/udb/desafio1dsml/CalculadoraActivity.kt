package edu.udb.desafio1dsml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.RoundingMode

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var etDisplay: EditText
    private var operand1: BigDecimal? = null
    private var operand2: BigDecimal? = null
    private var operator: String? = null
    private var clearNext: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        etDisplay = findViewById(R.id.etDisplay)

        val buttonClickListener = View.OnClickListener { view ->
            val button = view as Button
            if (clearNext) {
                etDisplay.text.clear()
                clearNext = false
            }
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
            setOperator("+")
        }

        findViewById<Button>(R.id.btnSubtract).setOnClickListener {
            setOperator("-")
        }

        findViewById<Button>(R.id.btnMultiply).setOnClickListener {
            setOperator("*")
        }

        findViewById<Button>(R.id.btnDivide).setOnClickListener {
            setOperator("/")
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            operand2 = etDisplay.text.toString().replace(",", ".").toBigDecimalOrNull()
            val result = calculateResult()
            etDisplay.setText(result)
            operator = null
            clearNext = true
        }
    }

    private fun setOperator(op: String) {
        operator = op
        operand1 = etDisplay.text.toString().replace(",", ".").toBigDecimalOrNull()
        clearNext = true
    }

    private fun calculateResult(): String {
        return if (operand1 == null || operand2 == null || operator == null) {
            "Error: Operación inválida"
        } else {
            val result = when (operator) {
                "+" -> operand1!!.add(operand2!!)
                "-" -> operand1!!.subtract(operand2!!)
                "*" -> operand1!!.multiply(operand2!!)
                "/" -> if (operand2 != BigDecimal.ZERO) operand1!!.divide(operand2!!, 10, RoundingMode.HALF_UP) else return "Error: División por cero"
                else -> return "Error: Operación inválida"
            }
            formatResult(result)
        }
    }

    private fun formatResult(result: BigDecimal): String {
        return result.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString()
    }
}

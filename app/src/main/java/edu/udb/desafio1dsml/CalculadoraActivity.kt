package edu.udb.desafio1dsml

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var etOperando1: EditText
    private lateinit var etOperando2: EditText
    private lateinit var etOperacion: EditText
    private lateinit var btnCalcularOperacion: Button
    private lateinit var tvResultadoOperacion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        etOperando1 = findViewById(R.id.etOperando1)
        etOperando2 = findViewById(R.id.etOperando2)
        etOperacion = findViewById(R.id.etOperacion)
        btnCalcularOperacion = findViewById(R.id.btnCalcularOperacion)
        tvResultadoOperacion = findViewById(R.id.tvResultadoOperacion)

        btnCalcularOperacion.setOnClickListener {
            calcularOperacion()
        }
    }

    private fun calcularOperacion() {
        val operando1 = etOperando1.text.toString().toFloatOrNull()
        val operando2 = etOperando2.text.toString().toFloatOrNull()
        val operacion = etOperacion.text.toString()

        if (operando1 == null || operando2 == null || operacion.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos correctamente.", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = when (operacion) {
            "+" -> operando1 + operando2
            "-" -> operando1 - operando2
            "*" -> operando1 * operando2
            "/" -> if (operando2 != 0f) operando1 / operando2 else "Error: División por cero"
            else -> "Operación inválida"
        }

        tvResultadoOperacion.text = "Resultado: $resultado"
        tvResultadoOperacion.visibility = TextView.VISIBLE
    }
}
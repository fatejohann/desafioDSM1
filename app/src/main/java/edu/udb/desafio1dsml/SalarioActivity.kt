package edu.udb.desafio1dsml

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SalarioActivity : AppCompatActivity() {

    private lateinit var etNombreEmpleado: EditText
    private lateinit var etSalarioBase: EditText
    private lateinit var btnCalcularSalario: Button
    private lateinit var tvResultadoSalario: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        etNombreEmpleado = findViewById(R.id.etNombreEmpleado)
        etSalarioBase = findViewById(R.id.etSalarioBase)
        btnCalcularSalario = findViewById(R.id.btnCalcularSalario)
        tvResultadoSalario = findViewById(R.id.tvResultadoSalario)

        btnCalcularSalario.setOnClickListener {
            calcularDescuentos()
        }
    }

    private fun calcularDescuentos() {
        val nombre = etNombreEmpleado.text.toString()
        val salarioBase = etSalarioBase.text.toString().toFloatOrNull()

        if (nombre.isEmpty() || salarioBase == null) {
            Toast.makeText(this, "Por favor, complete todos los campos correctamente.", Toast.LENGTH_SHORT).show()
            return
        }

        val afp = salarioBase * 0.0725
        val isss = salarioBase * 0.03
        val renta = calcularRenta(salarioBase)
        val salarioNeto = salarioBase - afp - isss - renta

        val resultado = """
            Nombre: $nombre
            Salario Base: $salarioBase
            AFP: $afp
            ISSS: $isss
            Renta: $renta
            Salario Neto: $salarioNeto
        """.trimIndent()

        tvResultadoSalario.text = resultado
        tvResultadoSalario.visibility = TextView.VISIBLE
    }

    private fun calcularRenta(salarioBase: Float): Float {
        return when {
            salarioBase <= 472.0 -> 0f
            salarioBase <= 895.24 -> ((salarioBase - 472.0) * 0.1f + 17.67f).toFloat()
            salarioBase <= 2038.10 -> ((salarioBase - 895.24) * 0.2f + 60.0f).toFloat()
            else -> ((salarioBase - 2038.10) * 0.3f + 288.57f).toFloat()
        }
    }
}
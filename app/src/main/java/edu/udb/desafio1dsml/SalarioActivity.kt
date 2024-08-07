package edu.udb.desafio1dsml

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.text.*

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

        btnCalcularSalario.setOnClickListener { calcularDescuentos() }
    }

    private fun calcularDescuentos() {
        val nombre = etNombreEmpleado.text.toString()
        val salarioBase = etSalarioBase.text.toString().toFloatOrNull()

        if (nombre.isEmpty() || salarioBase == null) {
            mostrarMensaje("Por favor, complete todos los campos correctamente.")
            return
        }

        val afp = calcularAfp(salarioBase)
        val isss = calcularIsss(salarioBase)
        val renta = calcularRenta(salarioBase)
        val salarioNeto = salarioBase - afp - isss - renta

        mostrarResultado(nombre, salarioBase, afp, isss, renta, salarioNeto)
    }

    private fun calcularAfp(salarioBase: Float): Float {
        return formatDecimal(salarioBase * 0.0725f)
    }

    private fun calcularIsss(salarioBase: Float): Float {
        return formatDecimal(salarioBase * 0.03f)
    }

    private fun calcularRenta(salarioBase: Float): Float {
        val renta = when {
            salarioBase <= 472.0 -> 0f
            salarioBase <= 895.24 -> ((salarioBase - 472.0) * 0.1f + 17.67f)
            salarioBase <= 2038.10 -> ((salarioBase - 895.24) * 0.2f + 60.0f)
            else -> ((salarioBase - 2038.10) * 0.3f + 288.57f)
        }
        return formatDecimal(renta)
    }

    private fun formatDecimal(number: Number): Float {
        return String.format("%.1f", number).toFloat()
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun mostrarResultado(nombre: String, salarioBase: Float, afp: Float, isss: Float, renta: Float, salarioNeto: Float) {
        val resultado = """
            Nombre: $nombre
            Salario Base: ${formatDecimal(salarioBase)}
            AFP: ${formatDecimal(afp)}
            ISSS: ${formatDecimal(isss)}
            Renta: ${formatDecimal(renta)}
            Salario Neto: ${formatDecimal(salarioNeto)}
        """.trimIndent()

        tvResultadoSalario.text = resultado
        tvResultadoSalario.visibility = TextView.VISIBLE
    }
}

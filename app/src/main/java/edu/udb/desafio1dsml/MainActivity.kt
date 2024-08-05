package edu.udb.desafio1dsml

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPromedio: Button = findViewById(R.id.btnPromedio)
        val btnSalario: Button = findViewById(R.id.btnSalario)
        val btnCalculadora: Button = findViewById(R.id.btnCalculadora)

        btnPromedio.setOnClickListener {
            startActivity(Intent(this, PromedioActivity::class.java))
        }

        btnSalario.setOnClickListener {
            startActivity(Intent(this, SalarioActivity::class.java))
        }

        btnCalculadora.setOnClickListener {
            startActivity(Intent(this, CalculadoraActivity::class.java))
        }
    }
}


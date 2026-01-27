package com.example.calificacinnumrica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNota = findViewById<EditText>(R.id.etNota)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnCalcular.setOnClickListener {
            val textoNota = etNota.text.toString()

            // 1. Verificación de campo vacío
            if (textoNota.isBlank()) {
                etNota.error = getString(R.string.error_campo_obligatorio)
                return@setOnClickListener
            }

            // 2. Conversión segura
            val nota = textoNota.toIntOrNull()

            // 3. Validación de rango (0 - 100)
            if (nota == null || nota !in 0..100) {
                Toast.makeText(this, getString(R.string.error_nota_invalida), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 4. Lógica de calificación con colores de sistema
            val (letra, colorRes) = when {
                nota >= 90 -> "A" to android.R.color.holo_green_dark
                nota >= 80 -> "B" to android.R.color.holo_blue_dark
                nota >= 70 -> "C" to android.R.color.holo_orange_dark
                else -> "F" to android.R.color.holo_red_dark
            }

            // 5. Mostrar resultado usando el recurso de string con formato
            tvResultado.text = getString(R.string.resultado_formato, letra)
            tvResultado.setTextColor(ContextCompat.getColor(this, colorRes))
        }
    }
}
package com.example.khokhlovra_01_03

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity

class CalcActivity : ComponentActivity() {

    private val pricePerMeter = 100.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        val spType = findViewById<Spinner>(R.id.spType)
        val etMeters = findViewById<EditText>(R.id.etMeters)
        val btnCalc = findViewById<Button>(R.id.btnCalc)

        spType.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.flat_types)
        )

        findViewById<android.view.View>(R.id.btnBack).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        btnCalc.setOnClickListener {
            val meters = etMeters.text.toString().replace(',', '.').toDoubleOrNull()
            if (meters == null || meters <= 0) {
                Toast.makeText(this, "Введите количество метров", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val k = when (spType.selectedItemPosition) {
                0 -> 1.4
                1 -> 1.0
                2 -> 0.8
                else -> 1.1
            }
            val result = pricePerMeter * meters * k

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("meters", meters)
            intent.putExtra("type", spType.selectedItem.toString())
            intent.putExtra("result", result)
            startActivity(intent)
        }
    }
}

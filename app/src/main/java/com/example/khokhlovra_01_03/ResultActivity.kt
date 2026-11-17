package com.example.khokhlovra_01_03

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val meters = intent.getDoubleExtra("meters", 0.0)
        val type = intent.getStringExtra("type") ?: ""
        val result = intent.getDoubleExtra("result", 0.0)

        findViewById<TextView>(R.id.tvMeters).text = "Количество метров: $meters"
        val text = "$type\nСтоимость: ${"%.2f".format(result)} тыс. руб."
        findViewById<TextView>(R.id.tvResult).text = text
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()

        findViewById<Button>(R.id.btnRegistration).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}

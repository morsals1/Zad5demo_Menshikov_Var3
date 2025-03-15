package com.example.menshikovv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ActivityResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultText: TextView = findViewById(R.id.resultText)
        val result = intent.getDoubleExtra("RESULT", 0.0)
        resultText.text = "Result: $result"
    }
}

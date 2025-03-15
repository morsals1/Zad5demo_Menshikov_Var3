package com.example.menshikovv3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView

class ActivityCalculation : AppCompatActivity() {
    private lateinit var buttonBack: ImageButton
    private lateinit var buttonCalc: Button
    private lateinit var spinner: Spinner
    private lateinit var spinnerText: TextView
    private lateinit var resText: TextView
    private lateinit var metresText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation)

        buttonCalc = findViewById(R.id.buttonCalc)
        buttonBack = findViewById(R.id.imageBack)
        spinner = findViewById(R.id.spinner)
        spinnerText = findViewById(R.id.spinnerText)
        resText = findViewById(R.id.resText)
        metresText = findViewById(R.id.metresText)

        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinner.adapter = adapter
        }

        buttonBack.setOnClickListener {
            val intent = Intent(this, ActivityFlatBank::class.java)
            startActivity(intent)
        }

        buttonCalc.setOnClickListener {
            val area = metresText.text.toString().toDoubleOrNull()
            val selectedType = spinner.selectedItem.toString()
            val coef = when (selectedType) {
                "1. 1-о комнатная квартира" -> 1.4
                "2. 2-х комнатная квартира" -> 1.0
                "3. 3-х комнатная квартира" -> 0.8
                "4. Студия" -> 1.1
                else -> 1.0
            }

            if (area != null) {
                val res = 100 * area * coef
                val intent = Intent(this, ActivityResult::class.java)
                intent.putExtra("RESULT", res)
                startActivity(intent)
            } else {
                resText.text = "Invalid input"
            }
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                spinnerText.text = selectedItem
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Do nothing
            }
        }
    }
}

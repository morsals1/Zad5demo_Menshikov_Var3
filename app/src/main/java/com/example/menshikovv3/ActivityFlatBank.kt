package com.example.menshikovv3

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActivityFlatBank : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var login: EditText
    private lateinit var password: EditText
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flat_bank)

        button = findViewById(R.id.button_entrance)
        login = findViewById(R.id.login)
        password = findViewById(R.id.password)


        button.setOnClickListener {
            if(login.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {

                val sharedpref = getSharedPreferences("Key", MODE_PRIVATE)
                sharedpref.edit().apply(){
                    putString(login.text.toString(),"")
                    putString(password.text.toString(),"")
                    commit()
                }.apply()

                val intent = Intent(this, ActivityCalculation::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
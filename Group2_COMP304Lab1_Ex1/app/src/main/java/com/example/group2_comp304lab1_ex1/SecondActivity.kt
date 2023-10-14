package com.example.group2_comp304lab1_ex1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second).toString()

        val extras = getIntent().getStringExtra("InputText").toString()
        var txtOutput:TextView = findViewById(R.id.txtOutput)


        txtOutput.text = extras

    }
}
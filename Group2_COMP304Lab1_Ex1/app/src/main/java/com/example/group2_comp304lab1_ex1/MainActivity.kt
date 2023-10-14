package com.example.group2_comp304lab1_ex1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var sendButton: Button = findViewById(R.id.btnSend)
        sendButton.setOnClickListener{
            var inputText: EditText = findViewById(R.id.txtInput)
            print(inputText.text.toString())
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("InputText",inputText.text.toString())
            startActivity(intent)

        }
    }
}


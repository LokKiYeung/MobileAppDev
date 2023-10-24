package com.example.group2_comp304lab2_ex1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onEnterButtonClick(view: View) {
        val k = Intent(this, HomeTypes::class.java)
        startActivity(k)
    }
}
package com.example.group2_comp304lab3

import android.content.Intent
import android.os.Bundle

import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exerciseListView = findViewById<ListView>(R.id.exerciseListView)
        var targetActivity: Class<*>? = null

        // display screen based on user selection
        exerciseListView.setOnItemClickListener { parent, itemView, position, id ->
            when (id) {
                0L -> {
                    targetActivity = Exercise1Activity::class.java
                }
                1L -> {
                    targetActivity = Exercise2Activity::class.java
                }
                2L -> {
                    targetActivity = Exercise3Activity::class.java
                }
                else -> {
                    targetActivity = Exercise1Activity::class.java
                }
            }
            val intent = Intent(this, targetActivity)
            this.startActivity(intent)
        }


    }
}







package com.example.group2_comp304lab1_ex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ARActivity : AppCompatActivity() {
    private var tvTextView: TextView? = null

    private fun setText(msg:String) {
        if (tvTextView == null) {
            tvTextView = findViewById<TextView>(R.id.tvARActivity)
        }

        var text:String = tvTextView?.text.toString()
        text = "$text$msg Executed \n"
        tvTextView?.text = text
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aractivity)
        setText("onCreate")
    }

    override fun onStart() {
        super.onStart()
        setText("onStart")
    }

    override fun onStop() {
        super.onStop()
        setText("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        setText("onDestroy")
    }
}
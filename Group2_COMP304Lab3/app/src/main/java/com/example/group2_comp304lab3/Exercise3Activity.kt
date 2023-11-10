package com.example.group2_comp304lab3

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Exercise3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise3)

        // get the earth image
        val earthImageView: ImageView =
            findViewById<View>(R.id.imgEarth) as ImageView
        earthImageView.setImageResource(R.drawable.ic_earth)
        earthImageView.visibility = View.VISIBLE

        // get the sun image
        val sunImageView: ImageView =
            findViewById<View>(R.id.imgSun) as ImageView
        sunImageView.setImageResource(R.drawable.ic_sun)
        sunImageView.visibility = View.VISIBLE

        // set onclick listener for the start button
        val spinStartButton: Button = findViewById<View>(R.id.btnStartSpin) as Button
        spinStartButton.setOnClickListener { performAnimation() }

    }

    private fun performAnimation() {

        val earthImageView: ImageView =
            findViewById<View>(R.id.imgEarth) as ImageView

        // Load the animation and start it
        val earthAnimation = AnimationUtils.loadAnimation(this, R.anim.earth_animation)
        earthImageView.startAnimation(earthAnimation)
    }

}
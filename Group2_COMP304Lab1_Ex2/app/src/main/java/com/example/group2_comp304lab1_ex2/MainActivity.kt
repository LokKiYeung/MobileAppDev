package com.example.group2_comp304lab1_ex2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private var bottomFragment: BottomFragment? = null

    public fun sendMessage(messageID: String, message: String) {
        bottomFragment?.setText(messageID, message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBottomFragment()
        addTopFragment()
        sendMessage("$TAG.onCreate", "onCreate of $TAG Executed")
    }

    private fun addTopFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val topFragment = TopFragment()
        fragmentTransaction.add(R.id.fragmentContainerViewTop, topFragment).commit()
    }

    private fun addBottomFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        bottomFragment = BottomFragment()
        fragmentTransaction.add(R.id.fragmentContainerViewBottom, bottomFragment!!).commit()

    }

    override fun onStart() {
        super.onStart()
        sendMessage("$TAG.onStart", "onStart of $TAG Executed")
    }

    override fun onStop() {
        super.onStop()
        sendMessage("$TAG.onStop", "onStop of $TAG Executed")
    }

    override fun onDestroy() {
        super.onDestroy()
        sendMessage("$TAG.onDestroy", "onDestroy of $TAG Executed")
    }
}
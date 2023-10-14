package com.example.group2_comp304lab1_ex2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment


private const val TAG = "TopFragment"

class TopFragment : Fragment(R.layout.fragment_top) {

    private fun sendMessage(messageID:String, message: String) {
        var main: MainActivity = (activity as MainActivity)
        main.sendMessage(messageID, message)
    }

    private fun setActionText(action: String) {
        sendMessage("$TAG.$action", "$TAG $action executed")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity, "onCreate  of $TAG executed", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // display message
        setActionText("onViewCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var fragmentView = inflater.inflate(R.layout.fragment_top, container, false)
        var rbAIActity: RadioButton = fragmentView.findViewById<RadioButton>(R.id.rbAIActivity)
        var rbARActity: RadioButton = fragmentView.findViewById<RadioButton>(R.id.rbARActivity)

        // set listeners
        rbAIActity.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startActivity(Intent((activity as? MainActivity), AIActivity::class.java))
            }
        }

        rbARActity.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startActivity(Intent((activity as? MainActivity), ARActivity::class.java))
            }
        }

        // display message
        setActionText("onCreateView")
        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        setActionText("onStart")
        Toast.makeText(activity, "onStart  of $TAG executed", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        // display message
        setActionText("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        // display message
        setActionText("onDestroy")
    }

}
package com.example.group2_comp304lab1_ex2

import android.R.string
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


private const val TAG = "BottomFragment"

class BottomFragment : Fragment() {
    private var tvLifecycle: TextView? = null
    private var messages:String= "Life Cycle Methods:\n"

    public fun setText(messageID: String, message: String) {
        messages = "$messages$message\n"

        tvLifecycle?.let { textView ->
            Log.e(TAG,  messages)
            textView.text=messages
        }
    }

    private fun setActionText(action: String) {
        setText("$TAG.$action", "$TAG $action executed")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        Toast.makeText(activity, "onCreateView  of $TAG executed", Toast.LENGTH_SHORT).show()

        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_bottom, container, false)

        // initialize variable
        tvLifecycle = fragmentView.findViewById<TextView>(R.id.tvLifecycle)
        tvLifecycle?.movementMethod = ScrollingMovementMethod();

        // display message
        setActionText("onCreateView")
        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        setActionText("onStop")
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
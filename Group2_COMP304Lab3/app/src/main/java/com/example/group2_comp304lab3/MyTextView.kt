package com.example.group2_comp304lab3

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.text.SimpleDateFormat

class MyTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    private var mlongDate = ""
    private var longDate: String
        get() = mlongDate
        set(date) {
            mlongDate = date    // store the value
            val date = SimpleDateFormat("yyyy-MM-dd").parse(mlongDate)  // parse the date with yyyy-MM-dd format
            if (date != null) {
                text =SimpleDateFormat("MMMM d, yyyy").format(date) // format the date to long format (MMMM d, yyyy)
            }
        }


    init {
        // get custom attributes for this view
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.MyTextView
        )
        // check if the custom attribute exist
        if (a.hasValue(R.styleable.MyTextView_longDate)) {
            // read the custom attribute
            longDate=  a.getString(R.styleable.MyTextView_longDate).toString() ?: ""
        }
        a.recycle()
    }
}
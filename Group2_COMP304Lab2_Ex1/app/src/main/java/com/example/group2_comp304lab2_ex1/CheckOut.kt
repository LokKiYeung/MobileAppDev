package com.example.group2_comp304lab2_ex1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class CheckOut : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        // create radio buttons
        val rgCheckOut = findViewById<RadioGroup>(R.id.rgpCheckOut)

        val sharedPreferences = getSharedPreferences("home", Context.MODE_PRIVATE)
        for (selected in sharedPreferences.all) {
            var rdoCheckOut = RadioButton(this)
            rdoCheckOut.text = selected.key + " $" + selected.value
            rdoCheckOut.tag = selected.key
            rgCheckOut.addView(rdoCheckOut)
        }

    }

    fun onMakePaymentButtonClick(view: View) {
        val rgCheckOut = findViewById<RadioGroup>(R.id.rgpCheckOut)

        // get selected radio button
        val selectedRadioButtonId: Int = rgCheckOut.checkedRadioButtonId

        // check if anything selected
        if (selectedRadioButtonId == View.NO_ID) {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Warning")
                .setMessage("Please pick an option")
                .setPositiveButton("OK") { _, _ -> }
                .show()
        } else {
            // get checked radio button
            val rdoChecked: RadioButton = findViewById(selectedRadioButtonId)

            // show selected value and direct to the next page
            val yesNoDialogBuilder = AlertDialog.Builder(this)
            yesNoDialogBuilder.setTitle("Confirm")
                .setMessage("Confirm to select " + rdoChecked.tag + " ?")
                .setPositiveButton("Yes") { _, _ ->
                    val k = Intent(this, PaymentOption::class.java)
                    startActivity(k)
                }.setNegativeButton("No") { _, _ ->
                }.show()
        }

    }
}
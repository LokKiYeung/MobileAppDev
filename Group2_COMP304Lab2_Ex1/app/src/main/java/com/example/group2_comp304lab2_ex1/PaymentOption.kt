package com.example.group2_comp304lab2_ex1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PaymentOption : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_option)
    }

    fun onProceedButtonClick(view: View) {

        val rgpPaymentMethod = findViewById<RadioGroup>(R.id.rgpPaymentMethod)

        // get selected radio button
        val selectedRadioButtonId: Int = rgpPaymentMethod.checkedRadioButtonId

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
                .setMessage("Confirm to select " + rdoChecked.text + " ?")
                .setPositiveButton("Yes") { _, _ ->
                    if (rdoChecked.text == "Cash") {
                        val okDialogBuilder = AlertDialog.Builder(this)
                        okDialogBuilder.setTitle("Payment Confirmation")
                            .setMessage("Payment made. Thank you!")
                            .setPositiveButton("OK") { _, _ ->

                            }.show()

                    } else {
                        val k = Intent(this, PaymentInformation::class.java)
                        startActivity(k)
                    }

                }.setNegativeButton("No") { _, _ ->
                }.show()
        }

    }
}
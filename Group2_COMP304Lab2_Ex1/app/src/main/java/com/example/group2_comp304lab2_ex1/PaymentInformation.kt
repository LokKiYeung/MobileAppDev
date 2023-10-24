package com.example.group2_comp304lab2_ex1

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PaymentInformation : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etPreferredName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmailAddress: EditText
    private lateinit var etCardNumber: EditText
    private lateinit var etAddress: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_information)

        etName = findViewById(R.id.etName)
        etPreferredName = findViewById(R.id.etPreferredName)
        etPhone = findViewById(R.id.etPhone)
        etEmailAddress = findViewById(R.id.etEmailAddress)
        etCardNumber = findViewById(R.id.etCardNumber)
        etAddress = findViewById(R.id.etAddress)
    }

    fun onMakePaymentButtonClick(view: View) {
        if (validateFields()) {
            val result = """
   Name: ${etName.text} 
   Preferred Name: ${etPreferredName.text} 
   Phone: ${etPhone.text} 
   Email: ${etEmailAddress.text} 
   Card Number: ${etCardNumber.text} 
   Address: ${etAddress.text}
""".trimIndent()

            val okDialogBuilder = AlertDialog.Builder(this)
            okDialogBuilder.setTitle("Payment Confirmation")
                .setMessage("$result\r\n\nPayment made. Thank you!")
                .setPositiveButton("OK") { _, _ ->
                    // clear all preference
                    val preferenceName: String = resources.getString(R.string.preference_name)
                    val sharedPreferences =
                        getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()
                }.show()
        }

    }

    private fun validateFields(): Boolean {
        // validate name
        if (etName.length() == 0) {
            etName.error = "This field is required."
            return false
        }
        // validate preferred name
        if (etPreferredName.length() == 0) {
            etPreferredName.error = "This field is required."
            return false
        }

        // validate phone number
        if (etPhone.length() == 0) {
            etPhone.error = "This field is required."
            return false
        } else if (!Patterns.PHONE.matcher(etPhone.text).matches()) {
            etPhone.error = "Invalid phone number."
            return false
        } else if (etPhone.length() != 10) {
            etPhone.error = "Invalid phone number."
            return false
        }

        // validate email
        if (etEmailAddress.length() == 0) {
            etEmailAddress.error = "This field is required."
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmailAddress.text).matches()) {
            etEmailAddress.error = "Invalid Email address."
            return false
        }

        // validate card number
        if (etCardNumber.length() == 0) {
            etCardNumber.error = "This field is required."
            return false
        } else if (etCardNumber.length() != 14) {
            etCardNumber.error = "Invalid Card Number."
            return false
        }

        // validate address
        if (etAddress.length() == 0) {
            etAddress.error = "This field is required."
            return false
        }

        // after all validation return true.
        return true
    }
}
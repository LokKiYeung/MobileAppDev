package com.example.group2_comp304lab4.ui.test_update

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.group2_comp304lab4.R

const val EXTRA_TEST_ID = " com.example.group2_comp304lab4.ui.test_update.EXTRA_TEST_ID"
const val EXTRA_PATIENT_ID = " com.example.group2_comp304lab4.ui.test_update.EXTRA_PATIENT_ID"
const val EXTRA_NURSE_ID = " com.example.group2_comp304lab4.ui.test_update.EXTRA_NURSE_ID"

const val EXTRA_BPL = " com.example.group2_comp304lab4.ui.test_update.EXTRA_BPL"
const val EXTRA_BPH = " com.example.group2_comp304lab4.ui.test_update.EXTRA_BPH"
const val EXTRA_BPN = " com.example.group2_comp304lab4.ui.test_update.EXTRA_BPN"
const val EXTRA_HR = " com.example.group2_comp304lab4.ui.test_update.EXTRA_HR"
const val EXTRA_TEMPERATURE = " com.example.group2_comp304lab4.ui.test_update.EXTRA_TEMPERATURE"


class ViewTestInfoActivity : AppCompatActivity() {

    private lateinit var mode: Mode
    private var testId: Long = -1L

    private lateinit var etNurseId: EditText
    private lateinit var etBPL: EditText
    private lateinit var etBPH: EditText
    private lateinit var etBPN: EditText
    private lateinit var etHR: EditText
    private lateinit var etTemperature: EditText

    private sealed class Mode {
        object Add : Mode()
        object View : Mode()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_test_info)

        // get the views
        etNurseId = findViewById(R.id.etNurseId)
        etBPL = findViewById(R.id.etBPL)
        etBPH = findViewById(R.id.etBPH)
        etBPN = findViewById(R.id.etBPN)
        etHR = findViewById(R.id.etHR)
        etTemperature = findViewById(R.id.etTemperature)

        val btnSave = findViewById<Button>(R.id.btnSave)

        // check if it is to add or view test
        testId = intent.getLongExtra(EXTRA_TEST_ID, -1)
        mode = if (testId == -1L) Mode.Add else Mode.View

        // update view
        when (mode) {
            Mode.Add -> {
                title = "Add Test"
                etNurseId.setText(intent.getLongExtra(EXTRA_NURSE_ID, -1).toString())
            }
            Mode.View -> {
                title = "View Test"
                etNurseId.setText(intent.getLongExtra(EXTRA_NURSE_ID, -1).toString())
                etBPL.setText(intent.getDoubleExtra(EXTRA_BPL, -1.0).toString())
                etBPH.setText(intent.getDoubleExtra(EXTRA_BPH, -1.0).toString())
                etBPN.setText(intent.getDoubleExtra(EXTRA_BPN, -1.0).toString())
                etHR.setText(intent.getLongExtra(EXTRA_HR, -1).toString())
                etTemperature.setText(intent.getDoubleExtra(EXTRA_TEMPERATURE, -1.0).toString())
                etBPL.isEnabled=false
                etBPH.isEnabled=false
                etBPN.isEnabled=false
                etHR.isEnabled=false
                etTemperature.isEnabled=false
                btnSave.isVisible = false
            }
        }

        // set onclick listener for the save button
        btnSave.setOnClickListener {
            saveTest()
        }

    }

    private fun saveTest() {

        // get data from view
        val bpl = etBPL.text.trim().toString()
        val bph = etBPH.text.trim().toString()
        val bpn = etBPN.text.trim().toString()
        val hr = etHR.text.trim().toString()
        val temperature = etTemperature.text.trim().toString()

        // data validation
        if (bpl.isEmpty() || bph.isEmpty() || bpn.isEmpty() || hr.isEmpty() || temperature.isEmpty()) {
            Toast.makeText(this, "please fill in information", Toast.LENGTH_SHORT).show()
            return
        }
        if ((bpl.toDouble()<50.0 || bpl.toDouble()>200.0)|| (bph.toDouble()<50.0 || bph.toDouble()>200.0)
            || (bpn.toDouble()<50.0 || bpn.toDouble()>200.0) || (hr.toLong()<30 || hr.toLong()>200) ||
            (temperature.toDouble()<34.0 || temperature.toDouble()>45.0)) {
            Toast.makeText(this, "please fix value", Toast.LENGTH_SHORT).show()
            return
        }

        // pass data to parent activity
        val data = Intent()
        data.putExtra(EXTRA_TEST_ID, testId)
        data.putExtra(EXTRA_BPL, bpl.toDouble())
        data.putExtra(EXTRA_BPH, bph.toDouble())
        data.putExtra(EXTRA_BPN, bpn.toDouble())
        data.putExtra(EXTRA_HR, hr.toLong())
        data.putExtra(EXTRA_TEMPERATURE, temperature.toDouble())

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
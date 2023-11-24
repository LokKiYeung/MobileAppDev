package com.example.group2_comp304lab4.ui.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.group2_comp304lab4.HospitalApplication
import com.example.group2_comp304lab4.R
import com.example.group2_comp304lab4.adapter.TestListAdapter
import com.example.group2_comp304lab4.model.Test
import com.example.group2_comp304lab4.ui.test_update.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TestActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var nurseId = -1L
    private var patientId = -1L

    private val testViewModel: TestViewModel by viewModels {
        TestViewModelFactory((application as HospitalApplication).testRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // get the nurseId id
        val appPref = getSharedPreferences((application as HospitalApplication).PREF_NAME, MODE_PRIVATE) ?: return
        nurseId = appPref.getLong((application as HospitalApplication).PREF_NURSE_ID, -1)

        patientId = intent.getLongExtra(
            com.example.group2_comp304lab4.ui.patient_update.EXTRA_PATIENT_PATIENT_ID,
            -1
        )

        // get the views
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        recyclerView = findViewById(R.id.recyclerview)

        // set adapter for RecyclerView (tests) and set onClickListener
        val adapter = TestListAdapter(TestListAdapter.OnClickListener { test: Test ->
            onRecyclerClickListener(test)
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // observe the test list and update the cached copy in the adapter
        testViewModel.getAllTestsByPatientId(patientId).observe(this) { tests ->
            tests.let { adapter.submitList(it) }
        }

        // set OnClickListener for the floating action button (add test)
        fab.setOnClickListener {
            val intent = Intent(this, ViewTestInfoActivity::class.java)
            intent.putExtra(EXTRA_NURSE_ID, nurseId)
            updateResultLauncher.launch(intent)
        }
    }

    // handle result returned from ViewTestInfoActivity activity
    private val updateResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                if (data != null) {

                    // get the data
                    val bpl = data.getDoubleExtra(EXTRA_BPL, -1.0)
                    val bph = data.getDoubleExtra(EXTRA_BPH, -1.0)
                    val bpn = data.getDoubleExtra(EXTRA_BPN, -1.0)
                    val hr = data.getLongExtra(EXTRA_HR, -1)
                    val temperature = data.getDoubleExtra(EXTRA_TEMPERATURE, -1.0)

                    var test = Test(
                        patientId,
                        nurseId,
                        bpl,
                        bph,
                        bpn,
                        hr,
                        temperature
                    )

                    // add new test
                    testViewModel.insert(test)
                    Toast.makeText(this, "Test inserted!", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Test not saved!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    // handles item click in recycler view. Show ViewTestInfoActivity activity and pass test information
    private fun onRecyclerClickListener(test: Test) {
        val intent = Intent(this, ViewTestInfoActivity::class.java)
        intent.putExtra(EXTRA_TEST_ID, test.testId)
        intent.putExtra(EXTRA_PATIENT_ID, test.patientId)
        intent.putExtra(EXTRA_NURSE_ID, test.nurseId)
        intent.putExtra(EXTRA_BPL, test.BPL)
        intent.putExtra(EXTRA_BPH, test.BPH)
        intent.putExtra(EXTRA_BPN, test.BPN)
        intent.putExtra(EXTRA_HR, test.HR)
        intent.putExtra(EXTRA_TEMPERATURE, test.temperature)
        updateResultLauncher.launch(intent)
    }
}
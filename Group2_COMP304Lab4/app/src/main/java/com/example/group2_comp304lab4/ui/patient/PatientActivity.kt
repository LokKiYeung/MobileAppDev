package com.example.group2_comp304lab4.ui.patient

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
import com.example.group2_comp304lab4.adapter.PatientListAdapter
import com.example.group2_comp304lab4.model.Patient
import com.example.group2_comp304lab4.ui.patient_update.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PatientActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var nurseId = -1L
    private val patientViewModel: PatientViewModel by viewModels {
        PatientViewModelFactory((application as HospitalApplication).patientRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        // get nurse id
        val appPref = getSharedPreferences((application as HospitalApplication).PREF_NAME, MODE_PRIVATE) ?: return
        nurseId = appPref.getLong((application as HospitalApplication).PREF_NURSE_ID, -1)
        // get the views
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        recyclerView = findViewById(R.id.recyclerview)

        // set adapter for RecyclerView (patients) and set onClickListener
        val adapter = PatientListAdapter(PatientListAdapter.OnClickListener { patient: Patient ->
            onRecyclerClickListener(patient)
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // observe the patient list and update the cached copy in the adapter
        patientViewModel.allPatients.observe(this) { patients ->
            patients.let { adapter.submitList(it) }
        }

        // set OnClickListener for the floating action button (add patient)
        fab.setOnClickListener {
            val intent = Intent(this, UpdateInfoActivity::class.java)
            intent.putExtra(EXTRA_PATIENT_NURSE_ID, nurseId)
            updateResultLauncher.launch(intent)
        }
    }

    // handle result returned from UpdateInfoActivity activity
    private val updateResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                if (data != null) {

                    // get the data
                    val patientId = data.getLongExtra(EXTRA_PATIENT_PATIENT_ID, -1)
                    val firstname = data.getStringExtra(EXTRA_FIRSTNAME).toString()
                    val lastname = data.getStringExtra(EXTRA_LASTNAME).toString()
                    val department = data.getStringExtra(EXTRA_DEPARTMENT).toString()
                    val room = data.getStringExtra(EXTRA_ROOM).toString()

                    var patient = Patient(
                        firstname,
                        lastname,
                        department,
                        nurseId,
                        room.toLong()
                    )

                    // add new patient
                    if (patientId == -1L) {
                        patientViewModel.insert(patient)
                        Toast.makeText(this, "Patient inserted!", Toast.LENGTH_SHORT).show()
                    } else {
                        // update patient
                        patient.patientId = patientId

                        patientViewModel.update(patient)
                        Toast.makeText(this, "Patient updated!", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "Patient not saved!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    // handles item click in recycler view. Show UpdateInfoActivity activity and pass patient information
    private fun onRecyclerClickListener(patient: Patient) {
        val intent = Intent(this, UpdateInfoActivity::class.java)
        intent.putExtra(EXTRA_PATIENT_PATIENT_ID, patient.patientId)
        intent.putExtra(EXTRA_PATIENT_NURSE_ID, patient.nurseId)
        intent.putExtra(EXTRA_FIRSTNAME, patient.firstname)
        intent.putExtra(EXTRA_LASTNAME, patient.lastname)
        intent.putExtra(EXTRA_DEPARTMENT, patient.department)
        intent.putExtra(EXTRA_ROOM, patient.room)
        updateResultLauncher.launch(intent)
    }

}
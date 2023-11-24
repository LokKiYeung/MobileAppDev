package com.example.group2_comp304lab4.ui.patient_update

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.group2_comp304lab4.R
import com.example.group2_comp304lab4.ui.test.TestActivity

const val EXTRA_PATIENT_PATIENT_ID = " com.example.group2_comp304lab4.ui.patient_update.EXTRA_PATIENT_ID"
const val EXTRA_FIRSTNAME = " com.example.group2_comp304lab4.ui.patient_update.EXTRA_FIRSTNAME"
const val EXTRA_LASTNAME = " com.example.group2_comp304lab4.ui.patient_update.EXTRA_LASTNAME"
const val EXTRA_PATIENT_NURSE_ID = " com.example.group2_comp304lab4.ui.patient_update.EXTRA_NURSEID"
const val EXTRA_DEPARTMENT = " com.example.group2_comp304lab4.ui.patient_update.EXTRA_DEPARTMENT"
const val EXTRA_ROOM = " com.example.group2_comp304lab4.ui.patient_update.EXTRA_ROOM"

class UpdateInfoActivity : AppCompatActivity() {

    private lateinit var mode: Mode
    private var patientId: Long = -1L
    private var nurseId:Long = -1L
    private lateinit var etFirstname: EditText
    private lateinit var etLastname: EditText
    private lateinit var etNurseId: EditText
    private lateinit var spDepartment: Spinner
    private lateinit var spRoom: Spinner

    private sealed class Mode {
        object Add : Mode()
        object Edit : Mode()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_info)

        // get the views
        etFirstname = findViewById(R.id.etFirstname)
        etLastname = findViewById(R.id.etLastname)
        etNurseId = findViewById(R.id.etNurseId)
        spDepartment = findViewById(R.id.spDepartment)
        spRoom = findViewById(R.id.spRoom)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // prepare spinner for department
        val departments = resources.getStringArray(R.array.departments_array)
        if (departments != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, departments
            )
            spDepartment.adapter = adapter
        }

        // prepare spinner for room number
        val rooms = resources.getStringArray(R.array.rooms_array)
        if (spRoom != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, rooms
            )
            spRoom.adapter = adapter
        }

        // check if it is to add or edit patient
        patientId = intent.getLongExtra(EXTRA_PATIENT_PATIENT_ID, -1)
        nurseId = intent.getLongExtra(EXTRA_PATIENT_NURSE_ID,-1)
        mode = if (patientId == -1L) Mode.Add else Mode.Edit

        // update view
        when (mode) {
            Mode.Add -> {
                title = "Add Patient"

                etNurseId.setText(nurseId.toString())
            }
            Mode.Edit -> {
                title = "Edit Patient"
                etFirstname.setText(intent.getStringExtra(EXTRA_FIRSTNAME))
                etLastname.setText(intent.getStringExtra(EXTRA_LASTNAME))
                etNurseId.setText(nurseId.toString())

                val departmentId = intent.getStringExtra(EXTRA_DEPARTMENT)
                val departmentSpinnerPosition: Int = departments.indexOf(departmentId)
                spDepartment.setSelection(departmentSpinnerPosition)

                val roomId = intent.getLongExtra(EXTRA_ROOM, -1)
                val roomSpinnerPosition: Int = rooms.indexOf(roomId.toString())
                spRoom.setSelection(roomSpinnerPosition)
            }
        }

        // set onclick listener for the save button
        btnSave.setOnClickListener {
            savePatient()
        }
    }

    private fun savePatient() {

        // get data from view
        val firstname = etFirstname.text.trim().toString()
        val lastname = etLastname.text.trim().toString()
        val department = spDepartment.selectedItem.toString()
        val room = spRoom.selectedItem.toString()

        // data validation
        if (firstname.isEmpty() || lastname.isEmpty() || department.isEmpty() || room.isEmpty()) {
            Toast.makeText(this, "please fill in information", Toast.LENGTH_SHORT).show()
            return
        }

        // pass data to parent activity
        val data = Intent()
        data.putExtra(EXTRA_PATIENT_PATIENT_ID, patientId)
        data.putExtra(EXTRA_FIRSTNAME, firstname)
        data.putExtra(EXTRA_LASTNAME, lastname)
        data.putExtra(EXTRA_DEPARTMENT, department)
        data.putExtra(EXTRA_ROOM, room)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (mode == Mode.Edit) {
            menuInflater.inflate(R.menu.patient_menu, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.menu_view_tests -> {
                val intent = Intent(this, TestActivity::class.java)
                intent.putExtra(EXTRA_PATIENT_PATIENT_ID, patientId)

                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        // Clear the menu items before re-inflating based on the condition
        menu.clear()

        if (mode == Mode.Edit) {
            menuInflater.inflate(R.menu.patient_menu, menu)
        }
        return super.onPrepareOptionsMenu(menu)
    }
}
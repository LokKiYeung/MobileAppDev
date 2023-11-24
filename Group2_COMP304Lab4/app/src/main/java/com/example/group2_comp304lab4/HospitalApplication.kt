package com.example.group2_comp304lab4

import android.app.Application
import com.example.group2_comp304lab4.database.AppDatabase
import com.example.group2_comp304lab4.repository.NurseRepository
import com.example.group2_comp304lab4.repository.PatientRepository
import com.example.group2_comp304lab4.repository.TestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HospitalApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val patientRepository by lazy { PatientRepository(database.patientDao()) }
    val testRepository by lazy { TestRepository(database.testDao()) }
    val nurseRepository by lazy { NurseRepository(database.nurseDao()) }

    val PREF_NURSE_ID = "com.example.group2_comp304lab4.nurse_id"
    val PREF_NAME = "com.example.group2_comp304lab4"

}
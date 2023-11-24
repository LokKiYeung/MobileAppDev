package com.example.group2_comp304lab4.repository


import androidx.annotation.WorkerThread
import com.example.group2_comp304lab4.dao.PatientDao
import com.example.group2_comp304lab4.model.Patient
import kotlinx.coroutines.flow.Flow

class PatientRepository(private val patientDao: PatientDao) {

    val allPatients: Flow<List<Patient>> = patientDao.getAll()

    fun getAllByNurseId(id: Long): Flow<List<Patient>> = patientDao.getAllByNurseId(id)

    @WorkerThread
    suspend fun insert(patient: Patient) = patientDao.insert(patient)

    @WorkerThread
    suspend fun update(patient: Patient) = patientDao.update(patient)
}
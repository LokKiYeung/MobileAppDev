package com.example.group2_comp304lab4.repository

import androidx.annotation.WorkerThread
import com.example.group2_comp304lab4.dao.NurseDao
import com.example.group2_comp304lab4.model.Nurse

class NurseRepository(private val nurseDao: NurseDao) {

    @WorkerThread
    suspend fun insert(nurse: Nurse) = nurseDao.insert(nurse)

    suspend fun getNurse(nurseId: Long, password: String): Nurse =
        nurseDao.getNurse(nurseId, password)
}
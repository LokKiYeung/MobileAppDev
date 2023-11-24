package com.example.group2_comp304lab4.repository

import androidx.annotation.WorkerThread
import com.example.group2_comp304lab4.dao.TestDao
import com.example.group2_comp304lab4.model.Test


import kotlinx.coroutines.flow.Flow

class TestRepository(private val testDao: TestDao) {

    val allTests: Flow<List<Test>> = testDao.getAll()
    fun getAllByPatientId(id: Long): Flow<List<Test>> = testDao.getAllByPatientId(id)

    @WorkerThread
    suspend fun insert(test: Test) = testDao.insert(test)

    @WorkerThread
    suspend fun update(test: Test) = testDao.update(test)


}
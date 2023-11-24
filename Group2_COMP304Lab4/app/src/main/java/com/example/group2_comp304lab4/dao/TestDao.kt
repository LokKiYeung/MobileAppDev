package com.example.group2_comp304lab4.dao

import androidx.room.*
import com.example.group2_comp304lab4.model.Test
import kotlinx.coroutines.flow.Flow

@Dao
interface TestDao {

    @Query("select * from Tests order by testId asc")
    fun getAll(): Flow<List<Test>>

    @Query("select * from Tests where patientId = :patientId order by testId asc")
    fun getAllByPatientId(patientId: Long): Flow<List<Test>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(test: Test)

    @Query("delete from Tests")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(test: Test)

    @Update
    suspend fun update(test: Test)

}
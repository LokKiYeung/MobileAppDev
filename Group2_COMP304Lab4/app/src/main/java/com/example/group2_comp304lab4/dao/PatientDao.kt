package com.example.group2_comp304lab4.dao

import androidx.room.*
import com.example.group2_comp304lab4.model.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Query("select * from Patients order by patientId asc")
    fun getAll(): Flow<List<Patient>>

    @Query("select * from Patients where nurseId = :nurseId order by patientId asc")
    fun getAllByNurseId(nurseId: Long): Flow<List<Patient>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(patient: Patient)

    @Query("delete from Patients")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(patient: Patient)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(patient: Patient)

}
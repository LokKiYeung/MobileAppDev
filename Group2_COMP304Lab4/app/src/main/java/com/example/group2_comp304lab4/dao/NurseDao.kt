package com.example.group2_comp304lab4.dao

import androidx.room.*
import com.example.group2_comp304lab4.model.Nurse

@Dao
interface NurseDao {

    @Query("select * from Nurses WHERE nurseId = :nurseId and password = :password")
    suspend fun getNurse(nurseId: Long, password: String): Nurse

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nurse: Nurse)

    @Query("delete from Nurses")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(nurse: Nurse)

    @Update
    suspend fun update(nurse: Nurse)

}
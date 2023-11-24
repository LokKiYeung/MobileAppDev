package com.example.group2_comp304lab4.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Nurses")
data class Nurse(
    @ColumnInfo(name = "firstname")
    @NonNull
    var firstname: String,
    @ColumnInfo(name = "lastname")
    @NonNull
    var lastname: String,
    @ColumnInfo(name = "department")
    @NonNull
    var department: String,
    @ColumnInfo(name = "password")
    @NonNull
    var password: String,
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nurseId")
    @NonNull
    var nurseId: Long = 0
}

package com.example.group2_comp304lab4.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Patients")
data class Patient(
    @ColumnInfo(name = "firstname")
    @NonNull
    var firstname: String,

    @ColumnInfo(name = "lastname")
    @NonNull
    var lastname: String,

    @ColumnInfo(name = "department")
    @NonNull
    var department: String,

    @ColumnInfo(name = "nurseId")
    @NonNull
    var nurseId: Long,

    @ColumnInfo(name = "room")
    @NonNull
    var room: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "patientId")
    @NonNull
    var patientId: Long = 0
}

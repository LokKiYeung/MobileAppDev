package com.example.group2_comp304lab4.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tests")
data class Test(
    @ColumnInfo(name = "patientId")
    @NonNull
    var patientId: Long,

    @ColumnInfo(name = "nurseId")
    @NonNull
    var nurseId: Long,

    @ColumnInfo(name = "BPL")
    @NonNull
    var BPL: Double,    // Blood Pressure Low

    @ColumnInfo(name = "BPH")
    @NonNull
    var BPH: Double,    // Blood Pressure High

    @ColumnInfo(name = "BPN")
    @NonNull
    var BPN: Double,    // Blood Pressure Normal

    @ColumnInfo(name = "HR")
    @NonNull
    var HR: Long, // Heart rate

    @ColumnInfo(name = "temperature")
    @NonNull
    var temperature: Double,
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "testId")
    @NonNull
    var testId: Long = 0
}

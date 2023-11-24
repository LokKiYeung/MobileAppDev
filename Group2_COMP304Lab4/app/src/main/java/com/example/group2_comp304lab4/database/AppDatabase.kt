package com.example.group2_comp304lab4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.group2_comp304lab4.dao.NurseDao
import com.example.group2_comp304lab4.dao.PatientDao
import com.example.group2_comp304lab4.dao.TestDao
import com.example.group2_comp304lab4.model.Nurse
import com.example.group2_comp304lab4.model.Patient
import com.example.group2_comp304lab4.model.Test
import com.example.group2_comp304lab4.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(version = 1, entities = [Patient::class, Test::class, Nurse::class], exportSchema = true)
public abstract class AppDatabase : RoomDatabase() {

    abstract fun patientDao(): PatientDao
    abstract fun testDao(): TestDao
    abstract fun nurseDao(): NurseDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // create database if not exists
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constants.DB_NAME
                )
                    .addCallback(DBCallback(scope))
                    .fallbackToDestructiveMigration()
                    .createFromAsset("myapp.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DBCallback(
            private val scope: CoroutineScope
        ) : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }

            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                super.onDestructiveMigration(db)
            }

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {

                    }
                }
            }

        }
    }

}

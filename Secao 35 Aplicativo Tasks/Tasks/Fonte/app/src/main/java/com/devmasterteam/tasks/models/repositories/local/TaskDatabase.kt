package com.devmasterteam.tasks.models.repositories.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devmasterteam.tasks.models.entities.PriorityModel
import com.devmasterteam.tasks.models.repositories.dao.PriorityDAO

@Database(entities = [PriorityModel::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun priorityDao(): PriorityDAO
    companion object {

        private lateinit var INSTANCE: TaskDatabase
        fun getDatabase(context: Context): TaskDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(TaskDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, TaskDatabase::class.java, "tasksDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}
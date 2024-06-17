package com.gabrielcarvalhotp.convidados.connections

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gabrielcarvalhotp.convidados.dao.GuestDAO
import com.gabrielcarvalhotp.convidados.entities.Guest

@Database(entities = [Guest::class], version = 1)
abstract class GuestDatabase: RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {
        private lateinit var instance: GuestDatabase
        fun getInstance(context: Context): GuestDatabase {
            if (!Companion::instance.isInitialized) {
                synchronized(GuestDatabase::class) {
                    instance = Room.databaseBuilder(context, GuestDatabase::class.java, "guest")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}
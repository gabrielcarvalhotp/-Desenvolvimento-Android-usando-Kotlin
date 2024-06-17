package com.gabrielcarvalhotp.convidados.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.gabrielcarvalhotp.convidados.entities.Guest

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest: Guest)

    @Update
    fun update(guest: Guest)

    @Delete
    fun delete(guest: Guest)

    @Query("select * from guest")
    fun findAll(): List<Guest>

    @Query("select * from guest where guest_id = :id")
    fun findById(id: Int): Guest?


    @Query("select * from guest where present = :present")
    fun findByPresent(present: Boolean): List<Guest>
}
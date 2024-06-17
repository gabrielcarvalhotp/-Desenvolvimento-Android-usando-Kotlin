package com.devmasterteam.tasks.models.repositories.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.devmasterteam.tasks.models.entities.PriorityModel
import retrofit2.http.DELETE

@Dao
interface PriorityDAO {

    @Query("select * from priority")
    fun findAll(): List<PriorityModel>

    @Query("select * from priority where id = :id")
    fun findById(id: Int): PriorityModel

    @Insert
    fun save(list: List<PriorityModel>)

    @Query("delete from priority")
    fun clear()
}
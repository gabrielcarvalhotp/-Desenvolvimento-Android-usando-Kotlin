package com.gabrielcarvalhotp.convidados.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guest")
class Guest() {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "guest_id")
    var guest_id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "present")
    var present: Boolean = false

    fun isPresent(): Int {
        return if (present) 1 else 0
    }
}
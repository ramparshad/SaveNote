package com.example.todo.RoomDatabaseUse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemsTable")
data class ItemEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String
)





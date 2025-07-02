package com.example.todo.RoomDatabaseUse

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class database : RoomDatabase(){
    abstract fun itemDao(): Dao
}

package com.example.hydrotrack

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WaterEntry::class], version = 1)
abstract class WaterDatabase : RoomDatabase() {
    abstract fun waterDao(): WaterDao
}

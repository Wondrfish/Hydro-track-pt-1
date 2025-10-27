package com.example.hydrotrack

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_table")
data class WaterEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val glasses: Int
)

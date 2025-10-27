package com.example.hydrotrack

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDao {
    @Insert
    suspend fun insert(entry: WaterEntry)

    @Query("SELECT * FROM water_table ORDER BY id DESC")
    fun getAllEntries(): Flow<List<WaterEntry>>
}

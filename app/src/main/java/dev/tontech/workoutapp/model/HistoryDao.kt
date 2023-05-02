package dev.tontech.workoutapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(history: History)

    @Query("SELECT * FROM `history-table`")
    fun fetAllDates(): Flow<List<History>>
}
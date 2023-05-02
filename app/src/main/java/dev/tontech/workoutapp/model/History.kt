package dev.tontech.workoutapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history-table")
data class History(
    @PrimaryKey
    val date: String
)

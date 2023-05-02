package dev.tontech.workoutapp

import android.app.Application
import dev.tontech.workoutapp.database.HistoryDatabase

class WorkoutApplication: Application() {
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}
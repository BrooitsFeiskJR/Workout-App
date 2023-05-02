package dev.tontech.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import dev.tontech.workoutapp.databinding.ActivityHistoryctivityBinding
import dev.tontech.workoutapp.model.HistoryDao
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private var binding: ActivityHistoryctivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryctivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val dao = (application as WorkoutApplication).db.HistoryDao()
        getAllCompletedDates(dao)
    }

    private fun getAllCompletedDates(historyDao: HistoryDao) {
        lifecycleScope.launch {
            historyDao.fetAllDates().collect {allCompletedDates ->
                for (i in allCompletedDates) {
                    Log.e("Date: ", ""+i)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
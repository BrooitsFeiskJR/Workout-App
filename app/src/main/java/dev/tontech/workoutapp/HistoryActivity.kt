package dev.tontech.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.tontech.workoutapp.adapters.HistoryAdapter
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
               if (allCompletedDates.isNotEmpty()) {
                   binding?.tvHistory?.visibility = View.VISIBLE
                   binding?.rvHistory?.visibility = View.VISIBLE
                   binding?.tvNoDataAvailable?.visibility = View.INVISIBLE

                   binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                   val dates = ArrayList<String>()
                   for (date in allCompletedDates) {
                       dates.add(date.date)
                   }
                   binding?.rvHistory?.adapter = HistoryAdapter(dates)

               } else {
                   binding?.tvHistory?.visibility = View.GONE
                   binding?.rvHistory?.visibility = View.GONE
                   binding?.tvNoDataAvailable?.visibility = View.VISIBLE
               }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
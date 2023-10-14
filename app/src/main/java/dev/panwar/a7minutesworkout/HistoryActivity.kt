package dev.panwar.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.panwar.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    // create a binding for the layout
    private var binding: ActivityHistoryBinding? = null

    private lateinit var list:ArrayList<BMIModel>

    companion object {
        private const val EXERCISE_HISTORY_VIEW = "EXERCISE_HISTORY_VIEW"
        private const val BMI_HISTORY_VIEW = "BMI_HISTORY_VIEW"
    }
    // at starting we have metric view as current visible view
    private var currentVisibleView: String = EXERCISE_HISTORY_VIEW //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//inflate the layout
        binding = ActivityHistoryBinding.inflate(layoutInflater)
// bind the layout to this activity
        setContentView(binding?.root)

//Setting up the action bar in the History Screen Activity and
// adding a back arrow button and click event for it.)
        setSupportActionBar(binding?.toolbarHistoryActivity)

        val actionbar = supportActionBar//actionbar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true) //set back button
            actionbar.title = "HISTORY" // Setting a title in the action bar.
        }

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding!!.rgBtn.setOnCheckedChangeListener{_,checkId:Int->
            // Here if the checkId is METRIC UNITS view then make the view visible else US UNITS view.
            if (checkId == R.id.rbHistory) {
                makeVisibleExerciseHistoryView()
            } else {
                makeVisibleBmiHistoryView()
            }

        }

        val dao = (application as WorkOutApp).db.historyDao()
        val bmiDao = (application as WorkOutApp).dbBmi.bmiDao()
        getAllCompletedBmiHistory(bmiDao)
        getAllCompletedDates(dao)
    }

    /**
     * Function is used to get the list exercise completed dates.
     */

    private fun getAllCompletedBmiHistory(dao: BMIDao) {
        lifecycleScope.launch {
            dao.fetchALlWeight().collect { allCompletedDatesList->

                if (allCompletedDatesList.isNotEmpty()) {
                    // Here if the List size is greater then 0 we will display the item in the recycler view or else we will show the text view that no data is available.
                    binding?.rvHistoryBmi?.visibility = View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.GONE

                    // Creates a vertical Layout Manager
                    binding?.rvHistoryBmi?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    list=ArrayList()
                    for (date in allCompletedDatesList){
                        list.add(date)
                    }
                    val historyAdapter = HistoryBmiAdapter(ArrayList(list))

                    binding?.rvHistoryBmi?.adapter = historyAdapter
                } else {
                    binding?.rvHistoryBmi?.visibility = View.INVISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                }
                // END
            }
        }
    }

    private fun makeVisibleBmiHistoryView() {
        currentVisibleView = BMI_HISTORY_VIEW
        binding!!.rvHistory.visibility=View.GONE
        binding!!.tvHistory.visibility=View.GONE

        binding!!.rvHistoryBmi.visibility=View.VISIBLE
    }

    private fun makeVisibleExerciseHistoryView() {
        currentVisibleView = EXERCISE_HISTORY_VIEW
        binding!!.rvHistory.visibility=View.VISIBLE
        binding!!.tvHistory.visibility=View.VISIBLE
        binding!!.rvHistoryBmi.visibility=View.GONE
    }

    private fun getAllCompletedDates(historyDao: HistoryDao) {
        lifecycleScope.launch {
            historyDao.fetchALlDates().collect { allCompletedDatesList->
                // TODO(Step 3 :here the dates which were printed in log.
                //  We will pass that list to the adapter class which we have created and bind it to the recycler view.)
                // START
                if (allCompletedDatesList.isNotEmpty()) {
                    // Here if the List size is greater then 0 we will display the item in the recycler view or else we will show the text view that no data is available.
                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.GONE

                    // Creates a vertical Layout Manager
                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    // History adapter is initialized and the list is passed in the param.
                    val dates = ArrayList<String>()
                    for (date in allCompletedDatesList){
                        dates.add(date.date)
                    }
                    val historyAdapter = HistoryAdapter(ArrayList(dates))

                    // Access the RecyclerView Adapter and load the data into it
                    binding?.rvHistory?.adapter = historyAdapter
                } else {
                    binding?.tvHistory?.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                }
                // END
            }
        }

    }
    // END
    override fun onDestroy() {
        super.onDestroy()
// reset the binding to null to avoid memory leak
        binding = null
    }
}
package dev.panwar.a7minutesworkout

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import dev.panwar.a7minutesworkout.adapter.HistoryAdapter
import dev.panwar.a7minutesworkout.databinding.FragmentExerciseHistoryBinding
import dev.panwar.a7minutesworkout.viewmodel.ExerciseViewModel
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseHistoryFragment : Fragment() {

    private  var binding:FragmentExerciseHistoryBinding?=null
    private lateinit var exerciseViewModel: ExerciseViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentExerciseHistoryBinding.inflate(layoutInflater)

        return binding!!.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao=(requireContext().applicationContext as WorkOutApp)
        val exerciseViewModelFactory=dao.exerciseViewModelFactory
        exerciseViewModel= ViewModelProvider(this,exerciseViewModelFactory)[ExerciseViewModel::class.java]
        getAllCompletedDates(exerciseViewModel)

    }

    private fun getAllCompletedDates(exerciseViewModel: ExerciseViewModel) {
        lifecycleScope.launch {
            exerciseViewModel.getAllHistoryDates().observe(this@ExerciseHistoryFragment){ allCompletedDatesList->
                // TODO(Step 3 :here the dates which were printed in log.
                //  We will pass that list to the adapter class which we have created and bind it to the recycler view.)
                // START
                if (allCompletedDatesList.isNotEmpty()) {
                    // Here if the List size is greater then 0 we will display the item in the recycler view or else we will show the text view that no data is available.
                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.GONE

                    // Creates a vertical Layout Manager
                    binding?.rvHistory?.layoutManager = LinearLayoutManager(requireContext())

                    // History adapter is initialized and the list is passed in the param.
                    val dates = ArrayList<String>()
                    for (date in allCompletedDatesList){
                        dates.add(date.date)
                    }
                    val historyAdapter = HistoryAdapter(ArrayList(dates))

                    // Access the RecyclerView Adapter and load the data into it
                    binding!!.rvHistory.adapter = historyAdapter
                } else {
                    binding!!.tvHistory.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE
                }
                // END
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
// reset the binding to null to avoid memory leak
        binding = null
    }

}
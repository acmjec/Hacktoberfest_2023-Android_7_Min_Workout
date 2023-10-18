package dev.panwar.a7minutesworkout

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import dev.panwar.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    // create a binding for the layout
    private var binding: ActivityHistoryBinding? = null
    private lateinit var list:ArrayList<BMIModel>
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//inflate the layout
        binding = ActivityHistoryBinding.inflate(layoutInflater)
// bind the layout to this activity
        setContentView(binding?.root)

//Setting up the action bar in the History Screen Activity and
// adding a back arrow button and click event for it.)

        createActionBar()

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        //for viewPagerAdapter
        viewPagerLogic()

    }

    private fun createActionBar() {
        setSupportActionBar(binding?.toolbarHistoryActivity)

        val actionbar = supportActionBar//actionbar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true) //set back button
            actionbar.title = "HISTORY" // Setting a title in the action bar.
        }
    }

    private fun viewPagerLogic() {
        adapter= ViewPagerAdapter(supportFragmentManager,lifecycle)
        binding!!.tabLayout.addTab(binding!!.tabLayout.newTab().setText("Exercise History"))
        binding!!.tabLayout.addTab(binding!!.tabLayout.newTab().setText("BMI History"))

        val tabTextColors = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_selected),  // Selected state
                intArrayOf()                               // Default state
            ),
            intArrayOf(
                Color.WHITE,    // Selected tab text color
                resources.getColor(R.color.light_white1)  // Default tab text color
            )
        )
        binding!!.tabLayout.tabTextColors = tabTextColors
        binding!!.tabLayout.tabTextColors = tabTextColors
        binding!!.viewPager.adapter=adapter

        binding!!.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding!!.viewPager.currentItem=tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding!!.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding!!.tabLayout.selectTab(binding!!.tabLayout.getTabAt(position))
            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
// reset the binding to null to avoid memory leak
        binding = null
    }
}
package dev.panwar.a7minutesworkout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.panwar.a7minutesworkout.BmiHistoryFragment
import dev.panwar.a7minutesworkout.ExerciseHistoryFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle)
    :FragmentStateAdapter(fragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
      return  if (position==0){
            ExerciseHistoryFragment()
        }else{
            BmiHistoryFragment()
        }
    }
}
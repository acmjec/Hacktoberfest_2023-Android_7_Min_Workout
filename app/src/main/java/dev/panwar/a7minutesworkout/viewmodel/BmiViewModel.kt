package dev.panwar.a7minutesworkout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.panwar.a7minutesworkout.model.BMIModel
import dev.panwar.a7minutesworkout.repository.BmiRepository
import kotlinx.coroutines.launch

class BmiViewModel(private val repository: BmiRepository):ViewModel() {

      fun insert(bmiModel: BMIModel){
            viewModelScope.launch {
                  repository.insert(bmiModel)
            }
      }

      fun getAllWeight():LiveData<List<BMIModel>>{
           return repository.fetchAllWeight()
      }


}
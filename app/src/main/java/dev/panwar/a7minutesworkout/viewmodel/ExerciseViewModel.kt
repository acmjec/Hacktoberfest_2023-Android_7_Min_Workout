package dev.panwar.a7minutesworkout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.panwar.a7minutesworkout.model.HistoryEntity
import dev.panwar.a7minutesworkout.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository):ViewModel() {


    fun insertHistoryEntity(historyEntity: HistoryEntity) {
        viewModelScope.launch {
            repository.insertHistoryEntity(historyEntity)
        }
    }

    fun getAllHistoryDates(): LiveData<List<HistoryEntity>> {
        return repository.getAllHistoryDates()
    }

}
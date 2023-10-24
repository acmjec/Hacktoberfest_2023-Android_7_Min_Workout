package dev.panwar.a7minutesworkout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.panwar.a7minutesworkout.repository.ExerciseRepository

class ExerciseViewModelFactory(private val repository: ExerciseRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExerciseViewModel(repository) as T
    }
}
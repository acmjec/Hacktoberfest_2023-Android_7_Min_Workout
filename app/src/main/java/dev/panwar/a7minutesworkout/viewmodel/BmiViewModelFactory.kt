package dev.panwar.a7minutesworkout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.panwar.a7minutesworkout.repository.BmiRepository

class BmiViewModelFactory(private val repository: BmiRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BmiViewModel(repository) as T
    }
}
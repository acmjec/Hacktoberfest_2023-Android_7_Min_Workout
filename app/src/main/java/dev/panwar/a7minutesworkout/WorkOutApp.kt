package dev.panwar.a7minutesworkout

import android.app.Application
import dev.panwar.a7minutesworkout.db.BMIDatabase
import dev.panwar.a7minutesworkout.db.HistoryDatabase
import dev.panwar.a7minutesworkout.repository.BmiRepository
import dev.panwar.a7minutesworkout.repository.ExerciseRepository
import dev.panwar.a7minutesworkout.viewmodel.BmiViewModelFactory
import dev.panwar.a7minutesworkout.viewmodel.ExerciseViewModelFactory

// create the application class
class WorkOutApp: Application() {

    private val dbBmi: BMIDatabase by lazy {
        BMIDatabase.getInstance(this)
    }
    private val bmiRepository: BmiRepository by lazy {
        BmiRepository(dbBmi.bmiDao())
    }
    val bmiViewModelFactory: BmiViewModelFactory by lazy {
        BmiViewModelFactory(bmiRepository)
    }

    val db: HistoryDatabase by lazy {
        HistoryDatabase.getInstance(this)
    }
    private val exerciseRepository: ExerciseRepository by lazy {
        ExerciseRepository(db.historyDao())
    }
    val exerciseViewModelFactory: ExerciseViewModelFactory by lazy {
        ExerciseViewModelFactory(exerciseRepository)
    }
}
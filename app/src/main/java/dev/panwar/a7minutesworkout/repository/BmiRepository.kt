package dev.panwar.a7minutesworkout.repository

import androidx.lifecycle.LiveData
import dev.panwar.a7minutesworkout.db.BMIDao
import dev.panwar.a7minutesworkout.model.BMIModel

class BmiRepository(private val dao: BMIDao) {

    suspend fun insert(bmiModel: BMIModel){
        return dao.insert(bmiModel)
    }

    fun fetchAllWeight():LiveData<List<BMIModel>>{
        return dao.fetchALlWeight()
    }
}
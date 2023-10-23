package dev.panwar.a7minutesworkout.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.panwar.a7minutesworkout.model.BMIModel

//create a dao interface with insert method
@Dao
interface BMIDao {

    @Insert
    suspend fun insert(bmiModel: BMIModel)

    @Query("Select * from `weight-table`")
    fun fetchALlWeight():LiveData<List<BMIModel>>
}
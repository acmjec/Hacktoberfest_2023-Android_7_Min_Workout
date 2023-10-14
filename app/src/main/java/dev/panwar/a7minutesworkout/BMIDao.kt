package dev.panwar.a7minutesworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//create a dao interface with insert method
@Dao
interface BMIDao {

    @Insert
    suspend fun insert(bmiModel: BMIModel)

    @Query("Select * from `weight-table`")
    fun fetchALlWeight():Flow<List<BMIModel>>
}
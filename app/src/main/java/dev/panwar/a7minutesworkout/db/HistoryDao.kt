package dev.panwar.a7minutesworkout.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import dev.panwar.a7minutesworkout.model.HistoryEntity

//create a dao interface with insert method
@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("Select * from `history-table`")
    fun fetchALlDates():LiveData<List<HistoryEntity>>

    @Query("DELETE FROM `history-table`")
    suspend fun deleteDatabase()
}
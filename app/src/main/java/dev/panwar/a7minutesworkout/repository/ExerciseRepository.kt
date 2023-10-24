package dev.panwar.a7minutesworkout.repository
import androidx.lifecycle.LiveData
import dev.panwar.a7minutesworkout.db.HistoryDao
import dev.panwar.a7minutesworkout.model.HistoryEntity

class ExerciseRepository(private val dao: HistoryDao) {

    suspend fun insertHistoryEntity(historyEntity: HistoryEntity) {
        dao.insert(historyEntity)
    }

    fun getAllHistoryDates(): LiveData<List<HistoryEntity>> {
        return dao.fetchALlDates()
    }
}
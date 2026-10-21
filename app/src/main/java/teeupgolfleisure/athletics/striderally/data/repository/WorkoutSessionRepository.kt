package teeupgolfleisure.athletics.striderally.data.repository

import teeupgolfleisure.athletics.striderally.data.database.dao.WorkoutSessionDao
import teeupgolfleisure.athletics.striderally.data.entity.WorkoutSessionEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WorkoutSessionRepository(
    private val workoutSessionDao: WorkoutSessionDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend fun save(session: WorkoutSessionEntity): Long {
        return withContext(coroutineDispatcher) {
            workoutSessionDao.save(session)
        }
    }

    fun observeAll(): Flow<List<WorkoutSessionEntity>> {
        return workoutSessionDao.observeAll()
    }

    suspend fun deleteAll() {
        withContext(coroutineDispatcher) {
            workoutSessionDao.deleteAll()
        }
    }
}

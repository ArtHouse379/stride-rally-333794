package teeupgolfleisure.athletics.striderally.data.repository

import teeupgolfleisure.athletics.striderally.data.model.WorkoutModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WorkoutRepository {
    private val workouts = listOf<WorkoutModel>()

    fun observeAll(): Flow<List<WorkoutModel>> {
        return flowOf(workouts)
    }

    fun observeById(id: Int): Flow<WorkoutModel?> {
        val service = workouts.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): WorkoutModel? {
        return workouts.firstOrNull { service -> service.id == id }
    }
}

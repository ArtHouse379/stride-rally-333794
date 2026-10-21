package teeupgolfleisure.athletics.striderally.data.repository

import teeupgolfleisure.athletics.striderally.data.model.TipModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TipRepository {
    private val tips = listOf<TipModel>()

    fun observeAll(): Flow<List<TipModel>> {
        return flowOf(tips)
    }
}

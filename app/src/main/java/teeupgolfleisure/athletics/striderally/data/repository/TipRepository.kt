package teeupgolfleisure.athletics.striderally.data.repository

import teeupgolfleisure.athletics.striderally.data.model.TipModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TipRepository {
    private val tips = listOf(
        TipModel(1, "Start Easy", "Keep the first minutes relaxed so your stride can settle naturally."),
        TipModel(2, "Check the Course", "Review the route and elevation before joining a class or race."),
        TipModel(3, "Run Together", "Choose a group pace that lets everyone stay connected."),
        TipModel(4, "Fuel Before Racing", "Eat a familiar light meal well before the start time."),
        TipModel(5, "Track Your Effort", "Use pace and intensity together to judge each run."),
        TipModel(6, "Recover Well", "Walk, hydrate, and rest after hard intervals or races."),
    )

    fun observeAll(): Flow<List<TipModel>> {
        return flowOf(tips)
    }
}

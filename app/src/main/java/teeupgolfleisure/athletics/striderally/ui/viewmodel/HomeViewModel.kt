package teeupgolfleisure.athletics.striderally.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import teeupgolfleisure.athletics.striderally.data.datastore.DEFAULT_WEEKLY_GOAL
import teeupgolfleisure.athletics.striderally.data.model.Difficulty
import teeupgolfleisure.athletics.striderally.data.model.TipModel
import teeupgolfleisure.athletics.striderally.data.model.WorkoutModel
import teeupgolfleisure.athletics.striderally.data.model.WorkoutType
import teeupgolfleisure.athletics.striderally.data.repository.ProfileRepository
import teeupgolfleisure.athletics.striderally.data.repository.TipRepository
import teeupgolfleisure.athletics.striderally.data.repository.WorkoutRepository
import teeupgolfleisure.athletics.striderally.data.repository.WorkoutSessionRepository
import teeupgolfleisure.athletics.striderally.ui.state.DataUiState
import teeupgolfleisure.athletics.striderally.ui.state.WorkoutFilterMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.time.DayOfWeek
import java.time.LocalDate

class HomeViewModel(
    private val workoutRepository: WorkoutRepository,
    private val profileRepository: ProfileRepository,
    private val workoutSessionRepository: WorkoutSessionRepository,
    private val tipRepository: TipRepository,
) : ViewModel() {
    private val _filterMode = MutableStateFlow(WorkoutFilterMode.TYPE)
    val filterMode: StateFlow<WorkoutFilterMode>
        get() = _filterMode.asStateFlow()

    private val _selectedType = MutableStateFlow<WorkoutType?>(null)
    val selectedType: StateFlow<WorkoutType?>
        get() = _selectedType.asStateFlow()

    private val _selectedDifficulty = MutableStateFlow<Difficulty?>(null)
    val selectedDifficulty: StateFlow<Difficulty?>
        get() = _selectedDifficulty.asStateFlow()

    val workoutsState: StateFlow<DataUiState<List<WorkoutModel>>> =
        workoutRepository.observeAll()
            .map { workouts -> DataUiState.from(workouts) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DataUiState.Initial,
            )

    val weeklyGoal: StateFlow<Int> =
        profileRepository.observeWeeklyGoal()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DEFAULT_WEEKLY_GOAL,
            )

    val weeklyProgress: StateFlow<Int> =
        workoutSessionRepository.observeAll()
            .map { sessions ->
                val weekStart = LocalDate.now().with(DayOfWeek.MONDAY)
                val weekEnd = weekStart.plusDays(6)
                sessions.count { session ->
                    val date = session.timestamp.toLocalDate()
                    !date.isBefore(weekStart) && !date.isAfter(weekEnd)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = 0,
            )

    val tips: StateFlow<List<TipModel>> =
        tipRepository.observeAll()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList(),
            )

    fun onFilterModeToggled() {
        _filterMode.update { mode ->
            if (mode == WorkoutFilterMode.TYPE) WorkoutFilterMode.DIFFICULTY else WorkoutFilterMode.TYPE
        }
    }

    fun onTypeSelected(type: WorkoutType?) {
        _selectedType.update { type }
    }

    fun onDifficultySelected(difficulty: Difficulty?) {
        _selectedDifficulty.update { difficulty }
    }
}

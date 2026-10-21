package teeupgolfleisure.athletics.striderally.data.repository

import teeupgolfleisure.athletics.striderally.data.model.Difficulty
import teeupgolfleisure.athletics.striderally.data.model.ExerciseModel
import teeupgolfleisure.athletics.striderally.data.model.Intensity
import teeupgolfleisure.athletics.striderally.data.model.WorkoutModel
import teeupgolfleisure.athletics.striderally.data.model.WorkoutType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WorkoutRepository {
    private val workouts = listOf(
        WorkoutModel(
            id = 1,
            name = "First 5K Builder",
            exercises = listOf(
                ExerciseModel("Brisk Walk Warm-Up", 300_000L, null, null, 30_000L, Intensity.LOW),
                ExerciseModel("Easy Run", 600_000L, null, null, 90_000L, Intensity.MODERATE),
                ExerciseModel("Run-Walk Repeats", 480_000L, null, null, 90_000L, Intensity.MODERATE),
                ExerciseModel("Steady Finish", 300_000L, null, null, 60_000L, Intensity.MODERATE),
                ExerciseModel("Cool-Down Walk", 240_000L, null, null, null, Intensity.LOW),
            ),
            difficulty = Difficulty.BEGINNER,
            type = WorkoutType.ROAD_RUNNING,
            imageUrl = "https://images.unsplash.com/photo-1571008887538-b36bb32f4571",
        ),
        WorkoutModel(
            id = 2,
            name = "City Tempo Builder",
            exercises = listOf(
                ExerciseModel("Easy Jog", 480_000L, null, null, 60_000L, Intensity.LOW),
                ExerciseModel("Cadence Strides", null, 4, 20, 45_000L, Intensity.HIGH),
                ExerciseModel("Tempo Run", 900_000L, null, null, 120_000L, Intensity.HIGH),
                ExerciseModel("Fast Finish", 240_000L, null, null, 60_000L, Intensity.HIGH),
                ExerciseModel("Recovery Jog", 360_000L, null, null, null, Intensity.LOW),
            ),
            difficulty = Difficulty.INTERMEDIATE,
            type = WorkoutType.ROAD_RUNNING,
            imageUrl = "https://images.unsplash.com/photo-1552674605-db6ffd4facb5",
        ),
        WorkoutModel(
            id = 3,
            name = "Trail Start Session",
            exercises = listOf(
                ExerciseModel("Trail Walk Warm-Up", 300_000L, null, null, 30_000L, Intensity.LOW),
                ExerciseModel("Gentle Trail Run", 600_000L, null, null, 90_000L, Intensity.MODERATE),
                ExerciseModel("Uphill Power Hike", 300_000L, null, null, 60_000L, Intensity.MODERATE),
                ExerciseModel("Controlled Descent", 240_000L, null, null, 60_000L, Intensity.MODERATE),
                ExerciseModel("Easy Trail Finish", 300_000L, null, null, null, Intensity.LOW),
            ),
            difficulty = Difficulty.BEGINNER,
            type = WorkoutType.TRAIL_RUNNING,
            imageUrl = "https://images.unsplash.com/photo-1476480862126-209bfaa8edc8",
        ),
        WorkoutModel(
            id = 4,
            name = "Summit Hill Repeats",
            exercises = listOf(
                ExerciseModel("Rolling Trail Warm-Up", 600_000L, null, null, 60_000L, Intensity.MODERATE),
                ExerciseModel("Steep Hill Charge", 180_000L, null, null, 120_000L, Intensity.MAXIMUM),
                ExerciseModel("Ridge Tempo Run", 720_000L, null, null, 90_000L, Intensity.HIGH),
                ExerciseModel("Technical Descent", 360_000L, null, null, 90_000L, Intensity.HIGH),
                ExerciseModel("Hilltop Strides", null, 5, 15, 60_000L, Intensity.MAXIMUM),
                ExerciseModel("Trail Cool-Down", 480_000L, null, null, null, Intensity.LOW),
            ),
            difficulty = Difficulty.ADVANCED,
            type = WorkoutType.TRAIL_RUNNING,
            imageUrl = "https://images.unsplash.com/photo-1742638163833-b8f2d935edac",
        ),
        WorkoutModel(
            id = 5,
            name = "Social Run Meetup",
            exercises = listOf(
                ExerciseModel("Group Mobility", 300_000L, null, null, 30_000L, Intensity.LOW),
                ExerciseModel("Conversation-Pace Run", 900_000L, null, null, 90_000L, Intensity.MODERATE),
                ExerciseModel("Partner Pace Pickups", 360_000L, null, null, 60_000L, Intensity.HIGH),
                ExerciseModel("Pack Recovery Jog", 420_000L, null, null, 60_000L, Intensity.LOW),
                ExerciseModel("Group Cool-Down", 300_000L, null, null, null, Intensity.LOW),
            ),
            difficulty = Difficulty.BEGINNER,
            type = WorkoutType.GROUP_RUNS,
            imageUrl = "https://images.unsplash.com/photo-1552674605-db6ffd4facb5",
        ),
        WorkoutModel(
            id = 6,
            name = "Pack Interval Class",
            exercises = listOf(
                ExerciseModel("Team Jog Warm-Up", 600_000L, null, null, 60_000L, Intensity.LOW),
                ExerciseModel("Relay Accelerations", null, 4, 10, 60_000L, Intensity.HIGH),
                ExerciseModel("Pack Threshold Run", 720_000L, null, null, 120_000L, Intensity.HIGH),
                ExerciseModel("Partner Sprint Repeats", null, 6, 8, 75_000L, Intensity.MAXIMUM),
                ExerciseModel("Team Recovery Jog", 480_000L, null, null, null, Intensity.LOW),
            ),
            difficulty = Difficulty.ADVANCED,
            type = WorkoutType.GROUP_RUNS,
            imageUrl = "https://images.unsplash.com/photo-1552674605-db6ffd4facb5",
        ),
        WorkoutModel(
            id = 7,
            name = "10K Race Tune-Up",
            exercises = listOf(
                ExerciseModel("Race Warm-Up", 600_000L, null, null, 60_000L, Intensity.LOW),
                ExerciseModel("Starting-Line Strides", null, 4, 20, 45_000L, Intensity.HIGH),
                ExerciseModel("Goal-Pace Run", 900_000L, null, null, 120_000L, Intensity.HIGH),
                ExerciseModel("Finish-Kick Repeats", null, 4, 12, 60_000L, Intensity.MAXIMUM),
                ExerciseModel("Post-Race Jog", 420_000L, null, null, null, Intensity.LOW),
            ),
            difficulty = Difficulty.INTERMEDIATE,
            type = WorkoutType.RACE_PREP,
            imageUrl = "https://images.unsplash.com/photo-1777788613992-5f2655a97e72",
        ),
        WorkoutModel(
            id = 8,
            name = "Half Marathon Pace",
            exercises = listOf(
                ExerciseModel("Easy Race Warm-Up", 600_000L, null, null, 60_000L, Intensity.LOW),
                ExerciseModel("Controlled Pace Block", 1_200_000L, null, null, 180_000L, Intensity.MODERATE),
                ExerciseModel("Goal-Pace Block", 1_200_000L, null, null, 180_000L, Intensity.HIGH),
                ExerciseModel("Late-Race Surge", 300_000L, null, null, 90_000L, Intensity.MAXIMUM),
                ExerciseModel("Finish-Line Strides", null, 4, 15, 60_000L, Intensity.HIGH),
                ExerciseModel("Recovery Cool-Down", 600_000L, null, null, null, Intensity.LOW),
            ),
            difficulty = Difficulty.INTERMEDIATE,
            type = WorkoutType.RACE_PREP,
            imageUrl = "https://images.unsplash.com/photo-1777788613992-5f2655a97e72",
        ),
    )

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

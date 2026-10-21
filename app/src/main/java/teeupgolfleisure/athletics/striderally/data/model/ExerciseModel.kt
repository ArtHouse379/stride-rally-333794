package teeupgolfleisure.athletics.striderally.data.model

import androidx.annotation.StringRes
import teeupgolfleisure.athletics.striderally.R

data class ExerciseModel(
    val name: String,
    val duration: Long?,
    val sets: Int?,
    val repetitions: Int?,
    val restDuration: Long?,
    val intensity: Intensity,
)

enum class Intensity(@field:StringRes val titleRes: Int) {
    LOW(R.string.intensity_low),
    MODERATE(R.string.intensity_moderate),
    HIGH(R.string.intensity_high),
    MAXIMUM(R.string.intensity_maximum)
}
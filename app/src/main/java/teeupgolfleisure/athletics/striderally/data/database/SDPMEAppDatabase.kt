package teeupgolfleisure.athletics.striderally.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import teeupgolfleisure.athletics.striderally.data.database.converter.Converters
import teeupgolfleisure.athletics.striderally.data.database.dao.WorkoutSessionDao
import teeupgolfleisure.athletics.striderally.data.entity.WorkoutSessionEntity

@Database(
    entities = [WorkoutSessionEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class SDPMEAppDatabase : RoomDatabase() {

    abstract fun workoutSessionDao(): WorkoutSessionDao
}

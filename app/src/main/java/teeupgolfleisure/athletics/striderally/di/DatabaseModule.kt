package teeupgolfleisure.athletics.striderally.di

import androidx.room.Room
import teeupgolfleisure.athletics.striderally.data.database.SDPMEAppDatabase
import org.koin.dsl.module

private const val DB_NAME = "sdpme_sport_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = SDPMEAppDatabase::class.java,
            name = DB_NAME,
        ).build()
    }

    single { get<SDPMEAppDatabase>().workoutSessionDao() }
}

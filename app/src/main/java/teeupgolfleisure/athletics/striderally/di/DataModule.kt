package teeupgolfleisure.athletics.striderally.di

import teeupgolfleisure.athletics.striderally.data.repository.OnboardingRepository
import teeupgolfleisure.athletics.striderally.data.repository.ProfileRepository
import teeupgolfleisure.athletics.striderally.data.repository.TipRepository
import teeupgolfleisure.athletics.striderally.data.repository.WorkoutRepository
import teeupgolfleisure.athletics.striderally.data.repository.WorkoutSessionRepository
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        OnboardingRepository(
            onboardingDataStoreManager = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single { WorkoutRepository() }

    single { TipRepository() }

    single {
        WorkoutSessionRepository(
            workoutSessionDao = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single {
        ProfileRepository(
            profileDataStoreManager = get(),
            coroutineDispatcher = get(DispatchersQualifiers.IO)
        )
    }
}
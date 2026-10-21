package teeupgolfleisure.athletics.striderally.di

import teeupgolfleisure.athletics.striderally.ui.viewmodel.HistoryViewModel
import teeupgolfleisure.athletics.striderally.ui.viewmodel.HomeViewModel
import teeupgolfleisure.athletics.striderally.ui.viewmodel.OnboardingViewModel
import teeupgolfleisure.athletics.striderally.ui.viewmodel.ProfileViewModel
import teeupgolfleisure.athletics.striderally.ui.viewmodel.SplashViewModel
import teeupgolfleisure.athletics.striderally.ui.viewmodel.StatisticsViewModel
import teeupgolfleisure.athletics.striderally.ui.viewmodel.WorkoutViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        HomeViewModel(
            workoutRepository = get(),
            profileRepository = get(),
            workoutSessionRepository = get(),
            tipRepository = get()
        )
    }

    viewModel { (workoutId: Int) ->
        WorkoutViewModel(
            workoutId = workoutId,
            workoutRepository = get(),
            workoutSessionRepository = get()
        )
    }

    viewModel {
        HistoryViewModel(
            workoutSessionRepository = get()
        )
    }

    viewModel {
        StatisticsViewModel(
            workoutSessionRepository = get()
        )
    }

    viewModel {
        ProfileViewModel(
            profileRepository = get(),
            workoutSessionRepository = get()
        )
    }
}
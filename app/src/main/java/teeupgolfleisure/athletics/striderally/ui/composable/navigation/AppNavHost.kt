package teeupgolfleisure.athletics.striderally.ui.composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import teeupgolfleisure.athletics.striderally.ui.composable.screen.history.SDPMEHistoryScreen
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.SDPMEHomeScreen
import teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.SDPMEOnboardingScreen
import teeupgolfleisure.athletics.striderally.ui.composable.screen.profile.SDPMEProfileScreen
import teeupgolfleisure.athletics.striderally.ui.composable.screen.splash.SDPMESplashScreen
import teeupgolfleisure.athletics.striderally.ui.composable.screen.statistics.SDPMEStatisticsScreen
import teeupgolfleisure.athletics.striderally.ui.composable.screen.workout.SDPMEWorkoutScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            SDPMESplashScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(route = NavRoute.Onboarding) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Onboarding> {
            SDPMEOnboardingScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(NavRoute.Onboarding) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Home> {
            SDPMEHomeScreen(
                onWorkoutClick = { workoutId ->
                    navController.navigate(route = NavRoute.Workout(workoutId = workoutId))
                },
            )
        }

        composable<NavRoute.Workout> { backStackEntry ->
            val workout: NavRoute.Workout = backStackEntry.toRoute()
            SDPMEWorkoutScreen(
                workoutId = workout.workoutId,
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable<NavRoute.History> {
            SDPMEHistoryScreen()
        }

        composable<NavRoute.Statistics> {
            SDPMEStatisticsScreen()
        }

        composable<NavRoute.Profile> {
            SDPMEProfileScreen()
        }
    }
}
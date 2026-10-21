package teeupgolfleisure.athletics.striderally.di

import teeupgolfleisure.athletics.striderally.data.datastore.OnboardingDataStoreManager
import teeupgolfleisure.athletics.striderally.data.datastore.ProfileDataStoreManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { OnboardingDataStoreManager(androidContext()) }
    single { ProfileDataStoreManager(androidContext()) }
}
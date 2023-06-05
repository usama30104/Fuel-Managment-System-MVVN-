package com.example.fuelmanagementsystemwithfragments.di

import android.content.SharedPreferences
import com.example.fuelmanagementsystemwithfragments.data.dao.FuelEfficiencyDao
import com.example.fuelmanagementsystemwithfragments.data.dao.FuelManagementDao
import com.example.fuelmanagementsystemwithfragments.data.repository.FuelRepositoryImpl
import com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction.FuelRepository
import com.example.fuelmanagementsystemwithfragments.data.repository.abstaraction.SharedPrefRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun provideFuelRepository(
        fuelEfficiencyDao: FuelEfficiencyDao,
        fuelManagementDao: FuelManagementDao,
        sharedPreferences: SharedPreferences
    ): FuelRepository {
        return FuelRepositoryImpl(fuelEfficiencyDao, fuelManagementDao, sharedPreferences)
    }

    @Provides
    fun provideSharedPrefRepository(
        fuelEfficiencyDao: FuelEfficiencyDao,
        fuelManagementDao: FuelManagementDao,
        sharedPreferences: SharedPreferences
    ): SharedPrefRepository {
        return FuelRepositoryImpl(fuelEfficiencyDao, fuelManagementDao, sharedPreferences)
    }
}
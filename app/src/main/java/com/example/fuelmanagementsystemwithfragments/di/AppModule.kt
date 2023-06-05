package com.example.fuelmanagementsystemwithfragments.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.fuelmanagementsystemwithfragments.data.dao.FuelEfficiencyDao
import com.example.fuelmanagementsystemwithfragments.data.dao.FuelManagementDao
import com.example.fuelmanagementsystemwithfragments.data.database.FuelManagementDatabase
import com.example.fuelmanagementsystemwithfragments.utils.Constants.SHARED_PREF
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Singleton
    @Provides
    fun provideFuelManagementDatabaseDb(app: Application): FuelManagementDatabase {
        return Room.databaseBuilder(app, FuelManagementDatabase::class.java, "DATABASE_NAME")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFuelManagementDao(db: FuelManagementDatabase): FuelManagementDao {
        return db.getFuelManagementDao()
    }

    @Singleton
    @Provides
    fun provideFuelEfficiencyDao(db: FuelManagementDatabase): FuelEfficiencyDao {
        return db.getFuelEfficiencyDao()
    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
    }
}
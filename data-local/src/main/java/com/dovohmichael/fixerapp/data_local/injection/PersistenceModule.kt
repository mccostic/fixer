package com.dovohmichael.fixerapp.data_local.injection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.dovohmichael.fixerapp.data_local.db.AppDatabase
import com.dovohmichael.fixerapp.data_local.db.currency.CurrencyDao
import com.dovohmichael.fixerapp.data_local.db.rate.RateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "currency_database"
        ).build()

    @Provides
    fun provideCurrencyDao(appDatabase: AppDatabase): CurrencyDao = appDatabase.currencyDao()

    @Provides
    fun provideRateDao(appDatabase: AppDatabase): RateDao = appDatabase.rateDao()



}
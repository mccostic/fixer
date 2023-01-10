package com.dovohmichael.fixerapp.data_local.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.dovohmichael.fixerapp.data_local.db.currency.CurrencyDao
import com.dovohmichael.fixerapp.data_local.db.currency.CurrencyEntity
import com.dovohmichael.fixerapp.data_local.db.rate.RateDao
import com.dovohmichael.fixerapp.data_local.db.rate.RateEntity

@Database(entities = [CurrencyEntity::class,RateEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rateDao(): RateDao

    abstract fun currencyDao(): CurrencyDao

}
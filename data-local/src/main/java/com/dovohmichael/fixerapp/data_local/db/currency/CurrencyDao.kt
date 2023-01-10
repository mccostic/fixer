package com.dovohmichael.fixerapp.data_local.db.currency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency")
    fun getCurrencies(): Flow<List<CurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currencies: List<CurrencyEntity>)
}
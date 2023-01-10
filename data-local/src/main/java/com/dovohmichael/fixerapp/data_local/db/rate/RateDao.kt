package com.dovohmichael.fixerapp.data_local.db.rate

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dovohmichael.fixerapp.domain.entity.Rate
import kotlinx.coroutines.flow.Flow

@Dao
interface RateDao {

    @Query("SELECT * FROM rate where id=:id")
    fun getRate(id:String): Flow<List<Rate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRate(currencies: RateEntity)
}
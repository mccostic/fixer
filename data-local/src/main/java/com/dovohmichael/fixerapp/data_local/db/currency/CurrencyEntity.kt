package com.dovohmichael.fixerapp.data_local.db.currency

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "currency")
data class CurrencyEntity(
    @PrimaryKey
    @ColumnInfo(name = "iso") val iso: String,
    @ColumnInfo(name = "name") val name: String
)
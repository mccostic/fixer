package com.dovohmichael.fixerapp.data_local.db.rate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "rate")
data class RateEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "target") val target: String,
    @ColumnInfo(name = "base") val base: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "rate") val rate: Double,
)

package com.example.andev4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "date")
    val date: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "value")
    val value: Float?
)

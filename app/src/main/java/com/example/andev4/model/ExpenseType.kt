package com.example.andev4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseType(
    @PrimaryKey(autoGenerate = true)
    val typeId: Long = 0,
    @ColumnInfo(name = "typeName")
    val name: String?
)

package com.example.andev4.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.andev4.model.ExpenseType

@Dao
interface ExpenseTypeDao {
    @Query("SELECT * FROM ExpenseType")
    fun getAll(): List<ExpenseType>
    @Query("SELECT * FROM ExpenseType WHERE typeId IN (:typeId)" )
    fun loadAllByIds(typeId: IntArray): List<ExpenseType>
    @Query("SELECT * FROM ExpenseType WHERE typeName LIKE :name LIMIT 1")
    fun findByName(name: String): ExpenseType
    @Insert(onConflict = OnConflictStrategy .REPLACE)
    fun insert(expenseType: ExpenseType)
    @Insert
    fun insertAll(vararg types: ExpenseType)
    @Delete
    fun delete(expenseType: ExpenseType)
}
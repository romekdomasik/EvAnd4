package com.example.andev4.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.andev4.model.Expense

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    fun getAll(): LiveData<List<Expense>>
    @Query("SELECT * FROM Expense WHERE id IN (:expenseIds)")
    fun loadAllByIds(expenseIds: IntArray): List<Expense>
    @Query("SELECT * FROM Expense WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Expense
    @Insert(onConflict = OnConflictStrategy .REPLACE)
    fun insert(expense: Expense)
    @Insert
    fun insertAll(vararg expense: Expense)
    @Delete
    fun delete(expense: Expense)
}

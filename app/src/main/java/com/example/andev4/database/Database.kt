package com.example.andev4.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.andev4.dao.ExpenseDao
import com.example.andev4.dao.ExpenseTypeDao
import com.example.andev4.model.Expense
import com.example.andev4.model.ExpenseType
import com.example.andev4.model.TypeExpense

@Database(entities = [Expense::class, ExpenseType::class, TypeExpense::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun expenseTypeDao(): ExpenseTypeDao
}
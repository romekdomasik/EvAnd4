package com.example.andev4.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["id", "expenseId"])
data class TypeExpense(
    var id: Long,
    var expenseId: Long
)

data class ExpenseWithType(
    @Embedded
    val expense: Expense,
    @Relation(
        parentColumn = "id",
        entityColumn = "typeId",
        associateBy = Junction(TypeExpense::class)
    )
    val expenseType: List<ExpenseType>
)

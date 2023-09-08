package com.example.andev4.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.andev4.database.DataBase
import com.example.andev4.databinding.ActivityDepensesBinding
import com.example.andev4.model.Expense
import com.example.andev4.recyclerview.ExpenseAdapter


class DepensesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDepensesBinding

    lateinit var db: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepensesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(this, DataBase::class.java, "Sample.db")
            .build()
        val addBtn = binding.addButton
        addBtn.setOnClickListener {
            val intent = Intent(this, AjoutDepensesActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setupRecyclerView(gameList: List<Expense>){
        val rv = binding.RecyclerView
        val adapter = ExpenseAdapter(gameList)
        rv.layoutManager = LinearLayoutManager(this@DepensesActivity)
        rv.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        db.expenseDao().getAll().observeForever {
            setupRecyclerView(it)
        }
    }
}
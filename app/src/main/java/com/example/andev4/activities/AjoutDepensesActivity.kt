package com.example.andev4.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.andev4.database.DataBase
import com.example.andev4.databinding.ActivityAjoutDepensesBinding
import com.example.andev4.model.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AjoutDepensesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAjoutDepensesBinding

    lateinit var db: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjoutDepensesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // on configure la bouton back du xml
        val backButton = binding.backButton
        backButton.setOnClickListener {
            finish()
        }

        val saveButton = binding.addButton
        saveButton.setOnClickListener {
            if (!binding.nameEditText.text.toString().isEmpty() &&
                !binding.expenseDateEditText.text.toString().isEmpty() &&
                !binding.amountEditText.text.toString().isEmpty()){
                addExpense()
                finish()
            } else{
                showAlertDialog()
            }

        }

        db = Room.databaseBuilder(this, DataBase::class.java, "Sample.db").build()
    }

    // fonction qui stocke la coroutine qui contient l'ajout de livre(4)
    private fun addExpense(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                add()
            } catch (e: Exception){
                println("exception: $e")
            }
        }
    }

    suspend fun add(){
        withContext(Dispatchers.Default){
            val name = binding.nameEditText.text.toString()
            val value = binding.amountEditText.text.toString()
            val date = binding.expenseDateEditText.text.toString()
            val expenseDao = db.expenseDao()
            //var expenses = expenseDao.getAll()

            expenseDao.insert(
                Expense(
                name = name,
                value = value.toFloat(),
                date = date )
            )
        }

    }

    // configuration d'un alert dialog(6)
    private fun showAlertDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Message")
        builder.setMessage("Veuillez remplir tous les champs")

        builder.setPositiveButton("ok"){dialog, which ->
            dialog.dismiss()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}
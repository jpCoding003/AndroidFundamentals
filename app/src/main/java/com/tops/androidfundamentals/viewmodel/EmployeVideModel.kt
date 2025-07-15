package com.tops.androidfundamentals.viewmodel

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tops.androidfundamentals.model.EmployeeModel
import kotlin.apply

class EmployeVideModel : ViewModel() {

    private val _employelist = MutableLiveData<List<EmployeeModel>>()
     val employelist : LiveData<List<EmployeeModel>> = _employelist

    private lateinit var db: SQLiteDatabase
    fun loadEmployeData(context: Context) {

        db = context.openOrCreateDatabase("employe", Context.MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS employeData(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, role VARCHAR)")
        val cursor = db.rawQuery("SELECT * FROM employeData", null)

        val emplist = mutableListOf<EmployeeModel>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val role = cursor.getString(2)

                val emp = EmployeeModel(id,  name , role)
                emplist.add(emp)
            } while (cursor.moveToNext())
        }
        cursor.close()
        _employelist.value = emplist
    }

    fun addEmploye(context: Context, empname: String, emprole: String){

        db = context.openOrCreateDatabase("employe", Context.MODE_PRIVATE, null)
       db.execSQL("CREATE TABLE IF NOT EXISTS employeData(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, role VARCHAR)")
        var contentvalues = ContentValues().apply{

            put("name", empname)
            put("role",emprole)

        }
        db.insert("employeData", null, contentvalues)

        loadEmployeData(context)
    }

    fun deleteEmployee(context: Context, id: Int) {
        db = context.openOrCreateDatabase("employe", Context.MODE_PRIVATE, null)
        db.execSQL("DELETE FROM employeData WHERE id = $id")
        loadEmployeData(context) // Refresh the list
    }

}
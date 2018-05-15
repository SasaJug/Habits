package com.sasaj.habits.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.sasaj.habits.domain.Habit

class HabitDbTable(context: Context) {

    private val dbHelper = HabitDb(context).writableDatabase

    fun store(habit: Habit): Unit {

        val values = ContentValues()
        values.put(HabitEntry.TITLE_COL, habit.title)
        values.put(HabitEntry.DESCRIPTION_COL, habit.description)
        values.put(HabitEntry.IMAGE_COL, habit.imageUrl)

        dbHelper.transaction { insert(HabitEntry.TABLE_NAME, null, values) }

    }
}

fun SQLiteDatabase.transaction(function: SQLiteDatabase.()->Unit){
    this.beginTransaction()
    try {
        this.function()
        this.setTransactionSuccessful()
    }finally {
        this.close()
    }
}
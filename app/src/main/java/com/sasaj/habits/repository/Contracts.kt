package com.sasaj.habits.repository

/**
 * Created by sjugurdzija on 5/10/2018
 */

import android.provider.BaseColumns

val DATABASE_NAME = "habits.db"
val DATABASE_VERSION = 11

object HabitEntry : BaseColumns {
    val TABLE_NAME = "habit"
    val _ID = "id"
    val TITLE_COL = "title"
    val DESCRIPTION_COL = "description"
    val IMAGE_COL = "image"
}
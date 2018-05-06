package com.sasaj.habits.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.sasaj.habits.R
import com.sasaj.habits.R.id.addHabit
import com.sasaj.habits.domain.getSampleHabits
import com.sasaj.habits.ui.adapter.HabitsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = HabitsAdapter(getSampleHabits())
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            addHabit -> {
                switchToActivity(CreateHabitActivity::class.java)
            }
        }
        return true
    }

    fun switchToActivity(c : Class<*>): Unit{
        intent = Intent(this, c)
        startActivity(intent)
    }
}

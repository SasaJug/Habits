package com.sasaj.habits.domain
import com.sasaj.habits.R


/**
 * @author Peter Sommerhoff
 */
data class Habit(val title: String, val description: String, val imageUrl: String)


fun getSampleHabits(): List<Habit> {
    return listOf(
            Habit("Go for a walk",
                    "A nice walk in the sun gets you ready for the day ahead",
                    "https://cdn.pixabay.com/photo/2015/10/30/12/21/sport-1014015_960_720.jpg"),

            Habit("Drink a glass of water",
                    "A refreshing glass of water gets you hydrated",
                    "https://cdn.pixabay.com/photo/2013/07/13/13/26/drinking-glass-161034_960_720.png")
    )
}
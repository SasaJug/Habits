package com.sasaj.habits.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sasaj.habits.R
import com.sasaj.habits.domain.Habit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_card.view.*

class HabitsAdapter(val habits: List<Habit>) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {

    override fun getItemCount() = habits.size


    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        holder.card.tv_title.text = habit.title
        holder.card.tv_description.text = habit.description
        Picasso.get().load(habit.imageUrl).into(holder.card.iv_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_card, parent, false)
        return HabitViewHolder(view)
    }

    class HabitViewHolder(val card: View) : RecyclerView.ViewHolder(card)
}

package com.hodinv.mealstoeat.screen.mealslist

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.entity.Meal

class MealsAdapter(private val onDetail: (meal: Meal) -> Unit) : ListAdapter<Meal, MealsViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        return MealsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.listitem_meal, parent, false), onDetail)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.setNewMeal(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(oldMeal: Meal?, newMeal: Meal?): Boolean {
                return oldMeal?.idMeal == newMeal?.idMeal
            }

            override fun areContentsTheSame(oldMeal: Meal?, newMeal: Meal?): Boolean {
                return oldMeal?.idMeal == oldMeal?.idMeal &&
                        oldMeal?.strMealThumb == oldMeal?.strMealThumb &&
                        oldMeal?.strMeal == oldMeal?.strMeal
            }

        }
    }
}
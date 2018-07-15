package com.hodinv.mealstoeat.screen.mealslist

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.utils.GlideApp
import kotlinx.android.synthetic.main.listitem_meal.view.*

class MealsViewHolder(val view: View, onDetail: (meal: Meal) -> Unit) : RecyclerView.ViewHolder(view) {
    var meal: Meal? = null

    init {
        view.setOnClickListener {
            meal?.also {
                onDetail(it)
            }
        }
    }

    fun setNewMeal(newMeal: Meal) {
        meal = newMeal
        view.mealName.text = newMeal.strMeal

        GlideApp.with(view)
                .load(newMeal.strMealThumb)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view.mealImage)

    }
}
package com.hodinv.mealstoeat.screen.meal

import android.support.v7.widget.RecyclerView
import android.view.View
import com.hodinv.mealstoeat.model.entity.Ingredient
import kotlinx.android.synthetic.main.listitem_info.view.*
import kotlinx.android.synthetic.main.listitem_ingredient.view.*

class IngredientsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun setInfo(text: String) {
        view.info.text = text
    }

    fun setIngredient(ingredient: Ingredient) {
        view.name.text = ingredient.name
        view.measure.text = ingredient.measure
    }

}
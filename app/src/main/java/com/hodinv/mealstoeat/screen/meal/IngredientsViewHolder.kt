package com.hodinv.mealstoeat.screen.meal

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hodinv.mealstoeat.model.entity.Ingredient
import com.hodinv.mealstoeat.utils.GlideApp
import kotlinx.android.synthetic.main.listitem_info.view.*
import kotlinx.android.synthetic.main.listitem_ingredient.view.*
import java.net.URLEncoder

class IngredientsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun setInfo(text: String) {
        view.info.text = text
    }

    fun setIngredient(ingredient: Ingredient) {
        view.name.text = ingredient.name
        view.measure.text = ingredient.measure
        val url = "https://www.themealdb.com/images/ingredients/" +ingredient.name.replace(" ", "%20") + "-Small.png"
        GlideApp.with(view)
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view.image)
    }

}
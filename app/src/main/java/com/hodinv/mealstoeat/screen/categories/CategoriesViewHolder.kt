package com.hodinv.mealstoeat.screen.categories

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.entity.MealCategory
import com.hodinv.mealstoeat.utils.GlideApp
import com.hodinv.mealstoeat.utils.GlideAppModule
import kotlinx.android.synthetic.main.listitem_category.view.*

class CategoriesViewHolder(val view: View, onDetail: (category: MealCategory) -> Unit) : RecyclerView.ViewHolder(view) {
    var category: MealCategory? = null

    init {
        view.setOnClickListener {
            category?.also {
                onDetail(it)
            }
        }
    }

    fun setNewCategory(newCategory: MealCategory) {
        category = newCategory
        view.categoryName.text = newCategory.strCategory

        GlideApp.with(view)
                .load(newCategory.strCategoryThumb)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view.categoryImage)

    }
}
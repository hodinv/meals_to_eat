package com.hodinv.mealstoeat.screen.categories

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.entity.MealCategory

class CategoryAdapter(val onDetail: (category: MealCategory)->Unit) : ListAdapter<MealCategory, CategoriesViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
                LayoutInflater.from(parent?.context).inflate(R.layout.listitem_category, parent, false), onDetail)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.setNewCategory(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MealCategory>() {
            override fun areItemsTheSame(oldUser: MealCategory?, newUser: MealCategory?): Boolean {
                return oldUser?.idCategory == newUser?.idCategory;
            }

            override fun areContentsTheSame(oldUser: MealCategory?, newUser: MealCategory?): Boolean {
                return oldUser?.equals(newUser) ?: false
            }

        }
    }
}
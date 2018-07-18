package com.hodinv.mealstoeat.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class MealCategory(
        @PrimaryKey(autoGenerate = false)
        val idCategory: Int,
        val strCategory: String,
        val strCategoryThumb: String,
        val strCategoryDescription: String
)


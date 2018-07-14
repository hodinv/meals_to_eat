package com.hodinv.mealstoeat.model.repository

interface Repository {
    fun getMealCategoryDao(): MealCategoryDao
}
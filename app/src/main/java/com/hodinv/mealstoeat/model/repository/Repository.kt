package com.hodinv.mealstoeat.model.repository

interface Repository {
    fun getMealDao(): MealDao
}
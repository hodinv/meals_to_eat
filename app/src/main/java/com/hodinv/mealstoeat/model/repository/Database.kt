package com.hodinv.mealstoeat.model.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.entity.MealCategory

@Database(version = 3, entities = [MealCategory::class, Meal::class], exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun getMealCategoryDao(): MealCategoryDao
    abstract fun getMealDao(): MealDao
}
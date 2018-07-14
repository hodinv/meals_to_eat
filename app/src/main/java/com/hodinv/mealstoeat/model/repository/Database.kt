package com.hodinv.mealstoeat.model.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.hodinv.mealstoeat.model.entity.MealCategory

@Database(version = 1, entities = [MealCategory::class], exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun getMealCategoryDao(): MealCategoryDao
}
package com.hodinv.mealstoeat.model.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(version = 1, entities = [], exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun getMealDao(): MealDao
}
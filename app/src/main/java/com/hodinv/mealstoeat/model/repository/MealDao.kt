package com.hodinv.mealstoeat.model.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.hodinv.mealstoeat.model.entity.Meal
import io.reactivex.Flowable

@Dao
interface MealDao {

    @Insert
    fun addMeal(meal: Meal)

    @Query("SELECT * FROM Meal WHERE strCategory = :category")
    fun getMeals(category: String): Flowable<List<Meal>>

    @Query("DELETE FROM Meal WHERE idMeal = :id")
    fun deleteMealById(id: Int)

    @Update
    fun update(meal: Meal)
}
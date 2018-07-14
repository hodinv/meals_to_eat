package com.hodinv.mealstoeat.model.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.hodinv.mealstoeat.model.entity.MealCategory
import io.reactivex.Flowable

@Dao
interface MealCategoryDao {

    @Insert
    fun addCategory(category: MealCategory)

    @Query("SELECT * FROM MealCategory")
    fun getCategories(): Flowable<List<MealCategory>>

    @Query("DELETE FROM MealCategory WHERE idCategory = :id")
    fun deleteCategoryById(id: Int)

    @Update
    fun update(category: MealCategory)
}
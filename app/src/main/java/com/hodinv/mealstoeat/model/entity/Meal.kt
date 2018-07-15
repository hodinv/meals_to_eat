package com.hodinv.mealstoeat.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Meal(
        @PrimaryKey(autoGenerate = false)
        val idMeal: Int,
        val strMeal: String,
        val strMealThumb: String,
        var strCategory: String? = null,
        val strArea: String? = null,
        val strInstructions: String? = null,
        val strIngredient1: String? = null,
        val strIngredient2: String? = null,
        val strIngredient3: String? = null,
        val strIngredient4: String? = null,
        val strIngredient5: String? = null,
        val strIngredient6: String? = null,
        val strIngredient7: String? = null,
        val strIngredient8: String? = null,
        val strIngredient9: String? = null,
        val strIngredient10: String? = null,
        val strIngredient11: String? = null,
        val strIngredient12: String? = null,
        val strIngredient13: String? = null,
        val strIngredient14: String? = null,
        val strIngredient15: String? = null,
        val strIngredient16: String? = null,
        val strIngredient17: String? = null,
        val strIngredient18: String? = null,
        val strIngredient19: String? = null,
        val strIngredient20: String? = null,
        val strMeasure1: String? = null,
        val strMeasure2: String? = null,
        val strMeasure3: String? = null,
        val strMeasure4: String? = null,
        val strMeasure5: String? = null,
        val strMeasure6: String? = null,
        val strMeasure7: String? = null,
        val strMeasure8: String? = null,
        val strMeasure9: String? = null,
        val strMeasure10: String? = null,
        val strMeasure11: String? = null,
        val strMeasure12: String? = null,
        val strMeasure13: String? = null,
        val strMeasure14: String? = null,
        val strMeasure15: String? = null,
        val strMeasure16: String? = null,
        val strMeasure17: String? = null,
        val strMeasure18: String? = null,
        val strMeasure19: String? = null,
        val strMeasure20: String? = null
) {
    fun getIngredients(): List<Ingredient> {
        val list = ArrayList<Ingredient>()
        if (strIngredient1 != null && strMeasure1 != null) {
            list.add(Ingredient(strIngredient1, strMeasure1))
        }
        if (strIngredient2 != null && strMeasure2 != null) {
            list.add(Ingredient(strIngredient2, strMeasure2))
        }
        if (strIngredient3 != null && strMeasure3 != null) {
            list.add(Ingredient(strIngredient3, strMeasure3))
        }
        if (strIngredient4 != null && strMeasure4 != null) {
            list.add(Ingredient(strIngredient4, strMeasure4))
        }
        if (strIngredient5 != null && strMeasure5 != null) {
            list.add(Ingredient(strIngredient5, strMeasure5))
        }
        if (strIngredient6 != null && strMeasure6 != null) {
            list.add(Ingredient(strIngredient6, strMeasure6))
        }
        if (strIngredient7 != null && strMeasure7 != null) {
            list.add(Ingredient(strIngredient7, strMeasure7))
        }
        if (strIngredient8 != null && strMeasure8 != null) {
            list.add(Ingredient(strIngredient8, strMeasure8))
        }
        if (strIngredient9 != null && strMeasure9 != null) {
            list.add(Ingredient(strIngredient9, strMeasure9))
        }
        if (strIngredient10 != null && strMeasure10 != null) {
            list.add(Ingredient(strIngredient10, strMeasure10))
        }
        if (strIngredient11 != null && strMeasure11 != null) {
            list.add(Ingredient(strIngredient11, strMeasure11))
        }
        if (strIngredient12 != null && strMeasure12 != null) {
            list.add(Ingredient(strIngredient12, strMeasure12))
        }
        if (strIngredient13 != null && strMeasure13 != null) {
            list.add(Ingredient(strIngredient13, strMeasure13))
        }
        if (strIngredient14 != null && strMeasure14 != null) {
            list.add(Ingredient(strIngredient14, strMeasure14))
        }
        if (strIngredient15 != null && strMeasure15 != null) {
            list.add(Ingredient(strIngredient15, strMeasure15))
        }
        if (strIngredient16 != null && strMeasure16 != null) {
            list.add(Ingredient(strIngredient16, strMeasure16))
        }
        if (strIngredient17 != null && strMeasure17 != null) {
            list.add(Ingredient(strIngredient17, strMeasure17))
        }
        if (strIngredient18 != null && strMeasure18 != null) {
            list.add(Ingredient(strIngredient18, strMeasure18))
        }
        if (strIngredient19 != null && strMeasure19 != null) {
            list.add(Ingredient(strIngredient19, strMeasure19))
        }
        if (strIngredient20 != null && strMeasure20 != null) {
            list.add(Ingredient(strIngredient20, strMeasure20))
        }
        return list
    }
}
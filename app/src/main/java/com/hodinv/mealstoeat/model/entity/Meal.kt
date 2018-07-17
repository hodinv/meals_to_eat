package com.hodinv.mealstoeat.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.util.StringUtil
import android.text.TextUtils

@Entity
data class Meal(
        @PrimaryKey(autoGenerate = false)
        val idMeal: Int,
        val strMeal: String,
        val strMealThumb: String,
        var strCategory: String? = null,
        val strArea: String? = null,
        val strYoutube: String? = null,
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
        if (!TextUtils.isEmpty(strIngredient1) && (!TextUtils.isEmpty(strMeasure1))) {
            list.add(Ingredient(strIngredient1!!, strMeasure1!!))
        }
        if (!TextUtils.isEmpty(strIngredient2) && (!TextUtils.isEmpty(strMeasure2))) {
            list.add(Ingredient(strIngredient2!!, strMeasure2!!))
        }
        if (!TextUtils.isEmpty(strIngredient3) && (!TextUtils.isEmpty(strMeasure3))) {
            list.add(Ingredient(strIngredient3!!, strMeasure3!!))
        }
        if (!TextUtils.isEmpty(strIngredient4) && (!TextUtils.isEmpty(strMeasure4))) {
            list.add(Ingredient(strIngredient4!!, strMeasure4!!))
        }
        if (!TextUtils.isEmpty(strIngredient5) && (!TextUtils.isEmpty(strMeasure5))) {
            list.add(Ingredient(strIngredient5!!, strMeasure5!!))
        }
        if (!TextUtils.isEmpty(strIngredient6) && (!TextUtils.isEmpty(strMeasure6))) {
            list.add(Ingredient(strIngredient6!!, strMeasure6!!))
        }
        if (!TextUtils.isEmpty(strIngredient7) && (!TextUtils.isEmpty(strMeasure7))) {
            list.add(Ingredient(strIngredient7!!, strMeasure7!!))
        }
        if (!TextUtils.isEmpty(strIngredient8) && (!TextUtils.isEmpty(strMeasure8))) {
            list.add(Ingredient(strIngredient8!!, strMeasure8!!))
        }
        if (!TextUtils.isEmpty(strIngredient9) && (!TextUtils.isEmpty(strMeasure9))) {
            list.add(Ingredient(strIngredient9!!, strMeasure9!!))
        }
        if (!TextUtils.isEmpty(strIngredient10) && (!TextUtils.isEmpty(strMeasure10))) {
            list.add(Ingredient(strIngredient10!!, strMeasure10!!))
        }
        if (!TextUtils.isEmpty(strIngredient11) && (!TextUtils.isEmpty(strMeasure11))) {
            list.add(Ingredient(strIngredient11!!, strMeasure11!!))
        }
        if (!TextUtils.isEmpty(strIngredient12) && (!TextUtils.isEmpty(strMeasure12))) {
            list.add(Ingredient(strIngredient12!!, strMeasure12!!))
        }
        if (!TextUtils.isEmpty(strIngredient13) && (!TextUtils.isEmpty(strMeasure13))) {
            list.add(Ingredient(strIngredient13!!, strMeasure13!!))
        }
        if (!TextUtils.isEmpty(strIngredient14) && (!TextUtils.isEmpty(strMeasure14))) {
            list.add(Ingredient(strIngredient14!!, strMeasure14!!))
        }
        if (!TextUtils.isEmpty(strIngredient15) && (!TextUtils.isEmpty(strMeasure15))) {
            list.add(Ingredient(strIngredient15!!, strMeasure15!!))
        }
        if (!TextUtils.isEmpty(strIngredient16) && (!TextUtils.isEmpty(strMeasure16))) {
            list.add(Ingredient(strIngredient16!!, strMeasure16!!))
        }
        if (!TextUtils.isEmpty(strIngredient17) && (!TextUtils.isEmpty(strMeasure17))) {
            list.add(Ingredient(strIngredient17!!, strMeasure17!!))
        }
        if (!TextUtils.isEmpty(strIngredient18) && (!TextUtils.isEmpty(strMeasure18))) {
            list.add(Ingredient(strIngredient18!!, strMeasure18!!))
        }
        if (!TextUtils.isEmpty(strIngredient19) && (!TextUtils.isEmpty(strMeasure19))) {
            list.add(Ingredient(strIngredient19!!, strMeasure19!!))
        }
        if (!TextUtils.isEmpty(strIngredient20) && (!TextUtils.isEmpty(strMeasure20))) {
            list.add(Ingredient(strIngredient20!!, strMeasure20!!))
        }
        return list
    }
}
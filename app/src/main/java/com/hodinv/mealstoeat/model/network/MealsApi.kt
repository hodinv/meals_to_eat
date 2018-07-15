package com.hodinv.mealstoeat.model.network

import com.hodinv.mealstoeat.model.entity.CategoriesResponse
import com.hodinv.mealstoeat.model.entity.MealsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("categories.php")
    fun getCategories(): Observable<CategoriesResponse>

    @GET("filter.php")
    fun getMeals(@Query("c") category: String): Observable<MealsResponse>

    @GET("lookup.php")
    fun getMeal(@Query("i") idMeal: Int): Observable<MealsResponse>

}
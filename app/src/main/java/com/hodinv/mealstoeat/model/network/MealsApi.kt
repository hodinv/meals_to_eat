package com.hodinv.mealstoeat.model.network

import com.hodinv.mealstoeat.model.entity.CategoriesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MealsApi {
    @GET("categories.php")
    fun getCategories(): Observable<CategoriesResponse>
}
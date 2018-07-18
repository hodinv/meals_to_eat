package com.hodinv.mealstoeat.screen.mealslist

import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.entity.MealCategory
import com.hodinv.mealstoeat.model.entity.MealsResponse
import com.hodinv.mealstoeat.model.network.MealsApi
import com.hodinv.mealstoeat.model.repository.MealDao
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException

class MealsListPresenterTest {
    private lateinit var presenter: MealsListPresenter
    private lateinit var api: MealsApi
    private lateinit var router: MealsListContract.Router
    lateinit var view: MealsListContract.View
    private lateinit var mealDao: MealDao
    private val category = MealCategory(1, "Cat1", "", "")

    private val list = listOf(
            Meal(1, "Meal1", "", "Cat1"),
            Meal(2, "Meal2", "", "Cat1"),
            Meal(3, "Meal3", "", "Cat1")
    )

    val resposne = MealsResponse(
            arrayOf(
                    Meal(1, "Meal1", ""),
                    Meal(4, "Meal4", "")
            )
    )

    @Before
    fun prepare() {
        api = Mockito.mock(MealsApi::class.java)
        mealDao = Mockito.mock(MealDao::class.java)
        presenter = MealsListPresenter(mealDao, api, category.strCategory)
        view = Mockito.mock(MealsListContract.View::class.java)
        router = Mockito.mock(MealsListContract.Router::class.java)
        presenter.router = router
        presenter.view = view
    }


    @Test
    fun testOnStart() {
        Mockito.`when`(api.getMeals(category.strCategory)).thenReturn(Observable.just(MealsResponse(list.toTypedArray())))
        Mockito.`when`(mealDao.getMeals(category.strCategory)).thenReturn(Flowable.just(list))
        presenter.onStart()
        Mockito.verify(api).getMeals(category.strCategory)
        Mockito.verify(mealDao, Mockito.times(2)).getMeals(category.strCategory)
        Mockito.verify(view).showMeals(list)
    }


    @Test
    fun testUpdateOnStart() {
        Mockito.`when`(api.getMeals(category.strCategory)).thenReturn(Observable.just(resposne))
        Mockito.`when`(mealDao.getMeals(category.strCategory)).thenReturn(Flowable.just(list))
        presenter.onStart()
        Mockito.verify(api).getMeals(category.strCategory)
        Mockito.verify(mealDao, Mockito.times(2)).getMeals(category.strCategory)
        Mockito.verify(mealDao).deleteMealById(2)
        Mockito.verify(mealDao).deleteMealById(3)
        Mockito.verify(mealDao, Mockito.never()).update(resposne.meals[0])
        Mockito.verify(mealDao).addMeal(resposne.meals[1])
    }


    @Test
    fun testNoInternet() {
        Mockito.`when`(api.getMeals(category.strCategory)).thenReturn(Observable.error(IOException()))
        Mockito.`when`(mealDao.getMeals(category.strCategory)).thenReturn(Flowable.just(list))
        presenter.onStart()
        Mockito.verify(api).getMeals(category.strCategory)
        Mockito.verify(mealDao, Mockito.times(2)).getMeals(category.strCategory)
        Mockito.verify(view).showMeals(list)
    }

    @Test
    fun testOpenCategory() {
        presenter.mealSelected(list[0])
        Mockito.verify(router).openMeal(list[0])
    }
}
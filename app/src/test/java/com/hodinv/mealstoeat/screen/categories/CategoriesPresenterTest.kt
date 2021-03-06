package com.hodinv.mealstoeat.screen.categories

import com.hodinv.mealstoeat.model.entity.CategoriesResponse
import com.hodinv.mealstoeat.model.entity.MealCategory
import com.hodinv.mealstoeat.model.network.MealsApi
import com.hodinv.mealstoeat.model.repository.MealCategoryDao
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException


class CategoriesPresenterTest {

    private lateinit var presenter: CategoriesPresenter
    private lateinit var api: MealsApi
    private lateinit var router: CategoriesContract.Router
    lateinit var view: CategoriesContract.View
    private lateinit var mealCategoryDao: MealCategoryDao

    private val list = listOf(
            MealCategory(1, "Cat1", "", ""),
            MealCategory(2, "Cat2", "", ""),
            MealCategory(3, "Cat3", "", "")
    )

    val response = CategoriesResponse(
            arrayOf(
                    MealCategory(1, "Cat1", "", ""),
                    MealCategory(4, "Cat4", "", "")
            )
    )

    @Before
    fun prepare() {
        api = Mockito.mock(MealsApi::class.java)
        mealCategoryDao = Mockito.mock(MealCategoryDao::class.java)
        presenter = CategoriesPresenter(mealCategoryDao, api)
        view = Mockito.mock(CategoriesContract.View::class.java)
        router = Mockito.mock(CategoriesContract.Router::class.java)
        presenter.router = router
        presenter.view = view
    }


    @Test
    fun testOnStart() {
        Mockito.`when`(api.getCategories()).thenReturn(Observable.just(CategoriesResponse(list.toTypedArray())))
        Mockito.`when`(mealCategoryDao.getCategories()).thenReturn(Flowable.just(list))
        presenter.onStart()
        Mockito.verify(api).getCategories()
        Mockito.verify(mealCategoryDao, Mockito.times(2)).getCategories()
        Mockito.verify(view).showCategories(list)
    }


    @Test
    fun testUpdateOnStart() {
        Mockito.`when`(api.getCategories()).thenReturn(Observable.just(response))
        Mockito.`when`(mealCategoryDao.getCategories()).thenReturn(Flowable.just(list))
        presenter.onStart()
        Mockito.verify(api).getCategories()
        Mockito.verify(mealCategoryDao, Mockito.times(2)).getCategories()
        Mockito.verify(mealCategoryDao).deleteCategoryById(2)
        Mockito.verify(mealCategoryDao).deleteCategoryById(3)
        Mockito.verify(mealCategoryDao).update(response.categories[0])
        Mockito.verify(mealCategoryDao).addCategory(response.categories[1])
    }


    @Test
    fun testNoInternet() {
        Mockito.`when`(api.getCategories()).thenReturn(Observable.error(IOException()))
        Mockito.`when`(mealCategoryDao.getCategories()).thenReturn(Flowable.just(list))
        presenter.onStart()
        Mockito.verify(api).getCategories()
        Mockito.verify(mealCategoryDao, Mockito.times(2)).getCategories()
        Mockito.verify(view).showCategories(list)
    }

    @Test
    fun testOpenCategory() {
        presenter.categorySelected(list[0])
        Mockito.verify(router).openCategory(list[0])
    }

}
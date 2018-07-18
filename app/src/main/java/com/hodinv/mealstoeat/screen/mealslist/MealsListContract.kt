package com.hodinv.mealstoeat.screen.mealslist

import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.mvp.MvpPresenter
import com.hodinv.mealstoeat.mvp.MvpRouter
import com.hodinv.mealstoeat.mvp.MvpView

interface MealsListContract {
    interface View : MvpView {
        fun showMeals(meals: List<Meal>)
    }

    interface Router : MvpRouter {
        fun openMeal(meal: Meal)
    }

    interface Presenter : MvpPresenter<View, Router> {
        fun mealSelected(meal: Meal)
    }
}
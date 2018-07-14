package com.hodinv.mealstoeat.screen.categories

import com.hodinv.mealstoeat.model.entity.MealCategory
import com.hodinv.mealstoeat.mvp.MvpPresenter
import com.hodinv.mealstoeat.mvp.MvpRouter
import com.hodinv.mealstoeat.mvp.MvpView

interface CategoriesContract {
    interface View : MvpView {
        fun showCategories(categories: List<MealCategory>)
    }

    interface Router : MvpRouter {
        fun openCategory(category: MealCategory)
    }

    interface Presenter : MvpPresenter<View, Router> {
        fun categorySelected(category: MealCategory)
    }
}
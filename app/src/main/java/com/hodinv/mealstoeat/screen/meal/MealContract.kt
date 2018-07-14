package com.hodinv.mealstoeat.screen.meal

import com.hodinv.mealstoeat.mvp.MvpPresenter
import com.hodinv.mealstoeat.mvp.MvpRouter
import com.hodinv.mealstoeat.mvp.MvpView

interface MealContract {
    interface View: MvpView
    interface Router: MvpRouter
    interface Presenter: MvpPresenter<View, Router>
}
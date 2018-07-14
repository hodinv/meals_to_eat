package com.hodinv.mealstoeat.screen.mealslist

import com.hodinv.mealstoeat.mvp.MvpPresenter
import com.hodinv.mealstoeat.mvp.MvpRouter
import com.hodinv.mealstoeat.mvp.MvpView

interface MealsListContract {
    interface View: MvpView
    interface Router: MvpRouter
    interface Presenter: MvpPresenter<View, Router>
}
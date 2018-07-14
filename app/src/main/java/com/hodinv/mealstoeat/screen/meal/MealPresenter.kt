package com.hodinv.mealstoeat.screen.meal

import com.hodinv.mealstoeat.model.repository.Repository
import com.hodinv.mealstoeat.mvp.BaseMvpPresenter

class MealPresenter(val repository: Repository) :
        BaseMvpPresenter<MealContract.View, MealContract.Router>(), MealContract.Presenter {

}
package com.hodinv.mealstoeat.screen.mealslist

import com.hodinv.mealstoeat.model.repository.Repository
import com.hodinv.mealstoeat.mvp.BaseMvpPresenter

class MealsListPresenter(val repository: Repository) :
        BaseMvpPresenter<MealsListContract.View, MealsListContract.Router>(), MealsListContract.Presenter {

}
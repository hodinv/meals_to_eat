package com.hodinv.mealstoeat.screen.mealslist

import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.repository.Repository
import com.hodinv.mealstoeat.mvp.BaseMvpPresenter

class MealsListPresenter(val repository: Repository, val categoryId: Int) :
        BaseMvpPresenter<MealsListContract.View, MealsListContract.Router>(), MealsListContract.Presenter {
    override fun mealSelected(meal: Meal) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}
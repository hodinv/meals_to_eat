package com.hodinv.mealstoeat.screen.meal

import com.hodinv.mealstoeat.model.repository.DatabaseProvider
import com.hodinv.mealstoeat.mvp.BaseMvpFragment

class MealFragment : BaseMvpFragment<MealContract.View, MealContract.Router, MealContract.Presenter>(), MealContract.View {
    override fun createPresenter(): MealContract.Presenter {
        return MealPresenter(DatabaseProvider.instance)
    }

    override fun getMvpView(): MealContract.View {
        return this
    }

    override fun getRouter(): MealContract.Router {
        return activity as MealContract.Router
    }

}
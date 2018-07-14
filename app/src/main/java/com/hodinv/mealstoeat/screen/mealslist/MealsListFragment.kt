package com.hodinv.mealstoeat.screen.mealslist

import com.hodinv.mealstoeat.model.repository.DatabaseProvider
import com.hodinv.mealstoeat.mvp.BaseMvpFragment

class MealsListFragment : BaseMvpFragment<MealsListContract.View, MealsListContract.Router, MealsListContract.Presenter>(), MealsListContract.View {
    override fun createPresenter(): MealsListContract.Presenter {
        return MealsListPresenter(DatabaseProvider.instance)
    }

    override fun getMvpView(): MealsListContract.View {
        return this
    }

    override fun getRouter(): MealsListContract.Router {
        return activity as MealsListContract.Router
    }

}
package com.hodinv.mealstoeat.screen.meal

import com.hodinv.mealstoeat.model.network.MealsApi
import com.hodinv.mealstoeat.model.repository.MealDao
import com.hodinv.mealstoeat.mvp.BaseMvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers

class MealPresenter(val dao: MealDao,
                    val api: MealsApi,
                    val idMeal: Int) :
        BaseMvpPresenter<MealContract.View, MealContract.Router>(), MealContract.Presenter {

    private var repositorySubscription: Disposable? = null
    private var apiSubscription: Disposable? = null

    override fun onStart() {
        repositorySubscription = dao.getMeal(idMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view?.showMeal(it)
                }

        apiSubscription = api.getMeal(idMeal)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .zipWith(dao.getMeal(idMeal).toObservable())
                .subscribe({
                    if (it.first.meals.size > 0) {
                        dao.update(it.first.meals[0])
                    }

                }, {
                    // do nothing - should be already done from db
                })
    }

    override fun onStop() {
        repositorySubscription?.dispose()
        repositorySubscription = null
        apiSubscription?.dispose()
        apiSubscription = null
    }
}
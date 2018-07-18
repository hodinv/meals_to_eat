package com.hodinv.mealstoeat.screen.mealslist

import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.network.MealsApi
import com.hodinv.mealstoeat.model.repository.MealDao
import com.hodinv.mealstoeat.mvp.BaseMvpPresenter
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers

class MealsListPresenter(
        private val dao: MealDao,
        private val api: MealsApi,
        val categoryName: String) :
        BaseMvpPresenter<MealsListContract.View, MealsListContract.Router>(), MealsListContract.Presenter {

    private var repositorySubscription: Disposable? = null
    private var apiSubscription: Disposable? = null

    override fun mealSelected(meal: Meal) {
        router?.openMeal(meal)
    }

    override fun onStart() {
        repositorySubscription = dao.getMeals(categoryName)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    view?.showMeals(it)
                }

        apiSubscription = api.getMeals(categoryName)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .zipWith(dao.getMeals(categoryName).toObservable())
                .subscribe( {
                    val listToRemove = ArrayList<Int>()

                    for(oldItem in it.second) {
                        var has = false
                        for(newItem in it.first.meals) {
                            if(newItem.idMeal == oldItem.idMeal) {
                                has = true
                            }
                        }
                        if(!has) {
                            listToRemove.add(oldItem.idMeal)
                        }
                    }

                    for(newItem in it.first.meals) {
                        var has = false
                        for(oldItem in it.second) {
                            if(newItem.idMeal == oldItem.idMeal) {
                                has = true
                            }
                        }

                        if(!has) {
                            newItem.strCategory = categoryName
                            dao.addMeal(newItem)
                        }
                    }

                    for(itemId in listToRemove) {
                        dao.deleteMealById(itemId)
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
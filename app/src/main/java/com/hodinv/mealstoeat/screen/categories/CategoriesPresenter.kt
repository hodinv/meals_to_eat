package com.hodinv.mealstoeat.screen.categories

import com.hodinv.mealstoeat.model.entity.MealCategory
import com.hodinv.mealstoeat.model.network.MealsApi
import com.hodinv.mealstoeat.model.repository.MealCategoryDao
import com.hodinv.mealstoeat.mvp.BaseMvpPresenter
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers

class CategoriesPresenter(private val dao: MealCategoryDao, private val api: MealsApi) :
        BaseMvpPresenter<CategoriesContract.View, CategoriesContract.Router>(), CategoriesContract.Presenter {


    override fun categorySelected(category: MealCategory) {
        router?.openCategory(category)
    }

    private var repositorySubscription: Disposable? = null
    private var apiSubscription: Disposable? = null

    override fun onStart() {
        repositorySubscription = dao.getCategories()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    view?.showCategories(it)
                }

        apiSubscription = api.getCategories()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .zipWith(dao.getCategories().toObservable())
                .subscribe( {
                    val listToRemove = ArrayList<Int>()

                    for(oldItem in it.second) {
                        var has = false
                        for(newItem in it.first.categories) {
                            if(newItem.idCategory == oldItem.idCategory) {
                                has = true
                            }
                        }
                        if(!has) {
                            listToRemove.add(oldItem.idCategory)
                        }
                    }

                    for(newItem in it.first.categories) {
                        var has = false
                        for(oldItem in it.second) {
                            if(newItem.idCategory == oldItem.idCategory) {
                                has = true
                            }
                        }

                        if(has) {
                            dao.update(newItem)
                        } else {
                            dao.addCategory(newItem)
                        }
                    }

                    for(itemId in listToRemove) {
                        dao.deleteCategoryById(itemId)
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
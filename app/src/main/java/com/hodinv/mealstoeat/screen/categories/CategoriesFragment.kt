package com.hodinv.mealstoeat.screen.categories

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.entity.MealCategory
import com.hodinv.mealstoeat.model.network.NetworkProvider
import com.hodinv.mealstoeat.model.repository.DatabaseProvider
import com.hodinv.mealstoeat.mvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_categories.*

open class CategoriesFragment : BaseMvpFragment<CategoriesContract.View, CategoriesContract.Router, CategoriesContract.Presenter>(), CategoriesContract.View {

    val adapter = CategoryAdapter({ presenter?.categorySelected(it) })

    override fun showCategories(categories: List<MealCategory>) {
        adapter.submitList(categories)
    }

    override fun createPresenter(): CategoriesContract.Presenter {
        return CategoriesPresenter(DatabaseProvider.instance.getMealCategoryDao(), NetworkProvider.instance.getMealApi())
    }

    override fun getMvpView(): CategoriesContract.View {
        return this
    }

    override fun getRouter(): CategoriesContract.Router {
        return activity as CategoriesContract.Router
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.setHasFixedSize(true)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity)
    }

}
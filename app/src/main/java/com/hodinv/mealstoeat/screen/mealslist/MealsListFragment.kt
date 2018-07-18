package com.hodinv.mealstoeat.screen.mealslist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.entity.MealCategory
import com.hodinv.mealstoeat.model.network.NetworkProvider
import com.hodinv.mealstoeat.model.repository.DatabaseProvider
import com.hodinv.mealstoeat.mvp.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_meals_list.*

class MealsListFragment : BaseMvpFragment<MealsListContract.View, MealsListContract.Router, MealsListContract.Presenter>(), MealsListContract.View {

    private val adapter = MealsAdapter({ presenter?.mealSelected(it) })

    override fun showMeals(meals: List<Meal>) {
        adapter.submitList(meals)
    }

    override fun createPresenter(): MealsListContract.Presenter {
        return MealsListPresenter(
                DatabaseProvider.instance.getMealDao(),
                NetworkProvider.instance.getMealApi(),
                arguments?.getString(KEY_CATEGORY_NAME) ?: "")
    }

    override fun getMvpView(): MealsListContract.View {
        return this
    }


    override fun onResume() {
        super.onResume()
        activity?.setTitle(R.string.title_meals)
    }

    override fun getRouter(): MealsListContract.Router {
        return activity as MealsListContract.Router
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_meals_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.setHasFixedSize(true)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity)
    }

    companion object {
        const val KEY_CATEGORY_NAME = "categoryName"

        fun getInstance(category: MealCategory): MealsListFragment {
            return MealsListFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_CATEGORY_NAME, category.strCategory)
                }
            }
        }
    }

}
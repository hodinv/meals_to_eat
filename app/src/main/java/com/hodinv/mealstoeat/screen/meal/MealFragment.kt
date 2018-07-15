package com.hodinv.mealstoeat.screen.meal

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.network.NetworkProvider
import com.hodinv.mealstoeat.model.repository.DatabaseProvider
import com.hodinv.mealstoeat.mvp.BaseMvpFragment
import com.hodinv.mealstoeat.utils.GlideApp
import kotlinx.android.synthetic.main.fragment_meal.*

class MealFragment : BaseMvpFragment<MealContract.View, MealContract.Router, MealContract.Presenter>(), MealContract.View {

    val adapter = IngredientsAdapter()

    override fun showMeal(meal: Meal) {
        adapter.setInfo(meal.strInstructions, meal.getIngredients())
        GlideApp.with(this)
                .load(meal.strMealThumb)
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mealImage)
    }

    override fun createPresenter(): MealContract.Presenter {
        return MealPresenter(
                DatabaseProvider.instance.getMealDao(),
                NetworkProvider.instance.getMealApi(),
                arguments?.getInt(KEY_MEAL_ID) ?: 0
        )
    }

    override fun getMvpView(): MealContract.View {
        return this
    }

    override fun getRouter(): MealContract.Router {
        return activity as MealContract.Router
    }


    override fun onResume() {
        super.onResume()
        activity?.title = arguments?.getString(KEY_MEAL_NAME)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoItems.setHasFixedSize(false)
        infoItems.adapter = adapter
        infoItems.layoutManager = LinearLayoutManager(activity)
    }

    companion object {
        val KEY_MEAL_NAME = "mealName"
        val KEY_MEAL_ID = "mealId"

        fun getInstance(meal: Meal): MealFragment {
            return MealFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_MEAL_NAME, meal.strMeal)
                    putInt(KEY_MEAL_ID, meal.idMeal)
                }
            }
        }
    }

}
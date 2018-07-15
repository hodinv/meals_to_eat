package com.hodinv.mealstoeat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.entity.MealCategory
import com.hodinv.mealstoeat.screen.categories.CategoriesContract
import com.hodinv.mealstoeat.screen.categories.CategoriesFragment
import com.hodinv.mealstoeat.screen.meal.MealContract
import com.hodinv.mealstoeat.screen.meal.MealFragment
import com.hodinv.mealstoeat.screen.mealslist.MealsListContract
import com.hodinv.mealstoeat.screen.mealslist.MealsListFragment

class MainActivity : AppCompatActivity(), CategoriesContract.Router, MealsListContract.Router, MealContract.Router {
    override fun openMeal(meal: Meal) {
        startFragmentWithStacking(MealFragment.getInstance(meal))
    }

    override fun openCategory(category: MealCategory) {
        startFragmentWithStacking(MealsListFragment.getInstance(category))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!intent.hasExtra(EXTRA_TESTING)) {
            startFragment(CategoriesFragment())
        }
    }


    /**
     * Replace current content fragment with new one
     * @param newFragment fragment to show
     */
    fun startFragment(newFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment)

        transaction.commit()
    }

    /**
     * Adds current fragment ot back stack and shows new fragment
     * @param newFragment fragment to show
     */
    fun startFragmentWithStacking(newFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment).addToBackStack(null)
        transaction.commit()
    }

    companion object {

        val EXTRA_TESTING = "testing"

        fun testingIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRA_TESTING, true)
            return intent
        }
    }
}

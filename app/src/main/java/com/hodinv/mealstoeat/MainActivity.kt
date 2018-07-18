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
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.net.Uri
import android.view.MenuItem


class MainActivity : AppCompatActivity(), CategoriesContract.Router, MealsListContract.Router, MealContract.Router {
    private var stack = false
    override fun playYouTube(url: String) {
        try {
            val webpage = Uri.parse(url)
            val myIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(myIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.error_not_player), Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }

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
        resetStack()
    }

    fun resetStack() {
        stack = false
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    /**
     * Adds current fragment ot back stack and shows new fragment
     * @param newFragment fragment to show
     */
    private fun startFragmentWithStacking(newFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment).addToBackStack(null)
        transaction.commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        stack = true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            if(stack) {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {

        const val EXTRA_TESTING = "testing"

        fun testingIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRA_TESTING, true)
            return intent
        }
    }
}

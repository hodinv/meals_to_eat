package com.hodinv.mealstoeat.screen.meal

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.internal.runner.junit4.statement.UiThreadStatement
import android.support.test.rule.ActivityTestRule
import com.hodinv.mealstoeat.MainActivity
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.entity.Meal
import junit.framework.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MealFragmentTest {
    val presenterMock = Mockito.mock(MealContract.Presenter::class.java)
    lateinit var fragment: MealFragment

    val meal = Meal(1, "NameOfMeal", "", strInstructions = "Some instructions")

    @Rule
    @JvmField
    var mActivityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {


        override fun getActivityIntent(): Intent {
            return MainActivity.testingIntent(InstrumentationRegistry.getInstrumentation().targetContext)
        }

        override fun afterActivityLaunched() {
            super.afterActivityLaunched()
            fragment = MealFragment.getInstance(meal)
            fragment.presenter = presenterMock
            activity.startFragment(fragment)
        }
    }


    @Test
    fun testProperTitle() {
        Espresso.onView(withId(R.id.mealImage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Assert.assertEquals(meal.strMeal, mActivityRule.activity.title)
    }

    @Test
    fun testShowInstructions() {
        UiThreadStatement.runOnUiThread {
            fragment.showMeal(meal)
        }
        Espresso.onView(ViewMatchers.withText(meal.strInstructions)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    // TODO: check for ingrideent
}
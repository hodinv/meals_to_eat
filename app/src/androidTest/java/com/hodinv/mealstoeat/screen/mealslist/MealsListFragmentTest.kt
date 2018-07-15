package com.hodinv.mealstoeat.screen.mealslist

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.hodinv.mealstoeat.MainActivity
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.entity.MealCategory
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@LargeTest

class MealsListFragmentTest {

    val presenterMock = Mockito.mock(MealsListContract.Presenter::class.java)
    lateinit var fragment: MealsListFragment

    val category = MealCategory(1, "", "", "")

    val list = listOf(
            Meal(1, "Meal1", ""),
            Meal(2, "Meal2", ""),
            Meal(3, "Meal3", "")
    )

    @Rule
    @JvmField
    var mActivityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {


        override fun getActivityIntent(): Intent {
            return MainActivity.testingIntent(InstrumentationRegistry.getInstrumentation().targetContext)
        }

        override fun afterActivityLaunched() {
            super.afterActivityLaunched()
            fragment = MealsListFragment.getInstance(category)
            fragment.presenter = presenterMock
            activity.startFragment(fragment)
        }
    }

    @Test
    fun testShowData() {
        fragment.showMeals(list)
        Espresso.onView(ViewMatchers.withId(R.id.list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Thread.sleep(5000)
        for (pos in 0..list.size - 1) {
            Espresso.onView(ViewMatchers.withText(list[pos].strMeal)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        }
    }

    @Test
    fun testClick() {
        fragment.showMeals(list)
        Espresso.onView(ViewMatchers.withId(R.id.list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Thread.sleep(5000)
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.mealName), ViewMatchers.withText(list[0].strMeal))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.mealName), ViewMatchers.withText(list[0].strMeal))).perform(ViewActions.click())
        Mockito.verify(presenterMock)?.mealSelected(list[0])

    }
}
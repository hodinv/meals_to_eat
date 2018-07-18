package com.hodinv.mealstoeat.screen.categories

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
import com.hodinv.mealstoeat.model.entity.MealCategory
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@LargeTest

class CategoriesFragmentTest {

    val presenterMock = Mockito.mock(CategoriesContract.Presenter::class.java)
    lateinit var fragment: CategoriesFragment

    private val list =  listOf(
            MealCategory(1, "Cat1", "", ""),
            MealCategory(2, "Cat2", "", ""),
            MealCategory(3, "Cat3", "", "")
        )

    @Rule
    @JvmField
    var mActivityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {


        override fun getActivityIntent(): Intent {
            return MainActivity.testingIntent(InstrumentationRegistry.getInstrumentation().targetContext)
        }

        override fun afterActivityLaunched() {
            super.afterActivityLaunched()
            fragment = CategoriesFragment()
            fragment.presenter = presenterMock
            activity.startFragment(fragment)
        }
    }

    @Test
    fun testShowData() {
        fragment.showCategories(list)
        Espresso.onView(ViewMatchers.withId(R.id.list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(5000)
        for (pos in 0 until list.size) {
            Espresso.onView(ViewMatchers.withText(list[pos].strCategory)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun testClick() {
        fragment.showCategories(list)
        Espresso.onView(ViewMatchers.withId(R.id.list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(5000)
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.categoryName), ViewMatchers.withText(list[0].strCategory))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.categoryName), ViewMatchers.withText(list[0].strCategory))).perform(ViewActions.click())
        Mockito.verify(presenterMock)?.categorySelected(list[0])

    }
}
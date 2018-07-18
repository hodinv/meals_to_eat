package com.hodinv.mealstoeat.screen.meal

import com.hodinv.mealstoeat.model.entity.Meal
import com.hodinv.mealstoeat.model.entity.MealsResponse
import com.hodinv.mealstoeat.model.network.MealsApi
import com.hodinv.mealstoeat.model.repository.MealDao
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class MealPresenterTest {
    private lateinit var presenter: MealPresenter
    private lateinit var api: MealsApi
    private lateinit var router: MealContract.Router
    lateinit var view: MealContract.View
    private lateinit var mealDao: MealDao

    private val meal = Meal(1, "Meal name", "", "category1")

    @Before
    fun prepare() {
        api = Mockito.mock(MealsApi::class.java)
        mealDao = Mockito.mock(MealDao::class.java)
        presenter = MealPresenter(mealDao, api, meal.idMeal)
        view = Mockito.mock(MealContract.View::class.java)
        router = Mockito.mock(MealContract.Router::class.java)
        presenter.router = router
        presenter.view = view


        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

    }

    @After
    fun clean() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()

    }

    @Test
    fun testOnStart() {
        Mockito.`when`(api.getMeal(meal.idMeal)).thenReturn(Observable.just(MealsResponse(arrayOf(meal))))
        Mockito.`when`(mealDao.getMeal(meal.idMeal)).thenReturn(Flowable.just(meal))
        presenter.onStart()
        Mockito.verify(api).getMeal(meal.idMeal)
        Mockito.verify(mealDao, Mockito.times(2)).getMeal(meal.idMeal)
        Mockito.verify(view).showMeal(meal)
    }


    @Test
    fun testUpdateOnStart() {
        Mockito.`when`(api.getMeal(meal.idMeal)).thenReturn(Observable.just(MealsResponse(arrayOf(meal))))
        Mockito.`when`(mealDao.getMeal(meal.idMeal)).thenReturn(Flowable.just(meal))
        presenter.onStart()
        Mockito.verify(api).getMeal(meal.idMeal)
        Mockito.verify(mealDao, Mockito.times(2)).getMeal(meal.idMeal)
        Mockito.verify(mealDao).update(meal)
        Mockito.verify(mealDao, Mockito.never()).addMeal(meal)


    }


    @Test
    fun testNoInternet() {
        Mockito.`when`(api.getMeal(meal.idMeal)).thenReturn(Observable.error(IOException()))
        Mockito.`when`(mealDao.getMeal(meal.idMeal)).thenReturn(Flowable.just(meal))
        presenter.onStart()
        Mockito.verify(view).showMeal(meal)
    }

    private val immediate = object : Scheduler() {
        override fun scheduleDirect(run: Runnable,
                                    delay: Long, unit: TimeUnit): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(
                    Executor { it.run() })
        }
    }
}
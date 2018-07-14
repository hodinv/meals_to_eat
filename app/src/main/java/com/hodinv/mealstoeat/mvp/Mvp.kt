package com.hodinv.mealstoeat.mvp

interface MvpView
interface MvpRouter
interface MvpPresenter<V : MvpView, R : MvpRouter> {

    /**
     * link to View
     */
    var view: V?
    /**
     * link to Router
     */
    var router: R?

    /**
     * Set view state according to model values
     */
    fun onStart()

    /**
     * Stops callbacks for view changes, receiver etc.
     */
    fun onStop()

}
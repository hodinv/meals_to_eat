package com.hodinv.mealstoeat.mvp

import android.arch.lifecycle.LifecycleObserver

open class BaseMvpPresenter<V : MvpView, R : MvpRouter> : MvpPresenter<V, R>, LifecycleObserver {

    override var view: V? = null
    override var router: R? = null

    override fun onStart() {
        // init UI here
        // subscribe for model changes here
    }

    override fun onStop() {
        // unsubscribe from model changes here
    }

}
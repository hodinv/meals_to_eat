package com.hodinv.mealstoeat

import android.app.Application
import com.hodinv.mealstoeat.model.network.NetworkProvider
import com.hodinv.mealstoeat.model.repository.DatabaseProvider

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseProvider.initialize(this)
        NetworkProvider.initialize(this)
    }
}
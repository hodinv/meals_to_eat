package com.hodinv.mealstoeat.model.network

import android.content.Context
import com.hodinv.mealstoeat.R
import com.hodinv.mealstoeat.model.repository.DatabaseProvider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkProvider(context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(context.getString(R.string.base_path))
            .build()

    fun getMealApi(): MealsApi {
        return retrofit.create(MealsApi::class.java)
    }

    companion object {
        /**
         * instance of Database provider
         */
        lateinit var instance: NetworkProvider
            private set

        /**
         * Initialize instance of database provider. Should be called in Application.onCreate
         */
        fun initialize(context: Context) {
            instance = NetworkProvider(context)
        }


    }
}
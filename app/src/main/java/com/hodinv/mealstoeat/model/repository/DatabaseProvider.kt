package com.hodinv.mealstoeat.model.repository

import android.arch.persistence.room.Room
import android.content.Context
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class DatabaseProvider private constructor(context: Context) {
    fun getMealDao(): MealDao {
        return db.getMealDao()
    }

    fun getMealCategoryDao(): MealCategoryDao {
        return db.getMealCategoryDao()
    }


    /**
     * Settings for Room database
     */
    private var db: Database = Room.databaseBuilder(context, Database::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()



    companion object {
        /**
         * instance of Database provider
         */
        lateinit var instance: DatabaseProvider
            private set

        /**
         * Initialize instance of database provider. Should be called in Application.onCreate
         */
        fun initialize(context: Context) {
            instance = DatabaseProvider(context)
        }


    }

}
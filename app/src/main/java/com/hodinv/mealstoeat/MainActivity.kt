package com.hodinv.mealstoeat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /**
     * Replace current content fragment with new one
     * @param newFragment fragment to show
     */
    private fun startFragment(newFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment)

        transaction.commit()
    }

    /**
     * Adds current fragment ot back stack and shows new fragment
     * @param newFragment fragment to show
     */
    private fun startFragmentWithStacking(newFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment).addToBackStack(null)
        transaction.commit()
    }
}

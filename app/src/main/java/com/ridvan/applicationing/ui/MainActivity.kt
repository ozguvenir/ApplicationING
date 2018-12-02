package com.ridvan.applicationing.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ridvan.applicationing.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addProjectListFragment()
    }

    private fun addProjectListFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container) ?: ProjectListFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
package com.ridvan.applicationing

import com.ridvan.applicationing.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
class ApplicationING : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }


    override fun onCreate() {
        super.onCreate()
    }
}
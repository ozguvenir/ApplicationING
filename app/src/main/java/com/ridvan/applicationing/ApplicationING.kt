package com.ridvan.applicationing

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
class ApplicationING : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate() {
        super.onCreate()
    }
}
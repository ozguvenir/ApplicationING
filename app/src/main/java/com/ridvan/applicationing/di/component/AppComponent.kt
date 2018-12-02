package com.ridvan.applicationing.di.component

import com.ridvan.applicationing.ApplicationING
import com.ridvan.applicationing.di.module.ApplicationModule
import com.ridvan.applicationing.di.module.FragmentModule
import com.ridvan.applicationing.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
@Singleton
@Component(modules = [ApplicationModule::class, FragmentModule::class, ViewModelModule::class, AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<ApplicationING> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ApplicationING>()
}
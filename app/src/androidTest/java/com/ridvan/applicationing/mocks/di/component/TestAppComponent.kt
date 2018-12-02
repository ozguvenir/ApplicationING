package com.ridvan.applicationing.mocks.di.component

import com.ridvan.applicationing.TestApplication
import com.ridvan.applicationing.di.module.FragmentModule
import com.ridvan.applicationing.di.module.ViewModelModule
import com.ridvan.applicationing.mocks.di.module.TestApplicationModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by ridvanozguvenir on 3.12.2018.
 */

@Singleton
@Component(modules = [TestApplicationModule::class, FragmentModule::class, AndroidSupportInjectionModule::class, ViewModelModule::class])
interface TestAppComponent : AndroidInjector<TestApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TestApplication>() {
        abstract fun appModule(appModule: TestApplicationModule): Builder
    }
}
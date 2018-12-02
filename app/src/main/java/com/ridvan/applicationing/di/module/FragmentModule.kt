package com.ridvan.applicationing.di.module

import com.ridvan.applicationing.ui.ProjectListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeProjectListFragment(): ProjectListFragment
}
package com.ridvan.applicationing.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.Gson
import com.ridvan.applicationing.ApplicationING
import com.ridvan.applicationing.persistence.AppDB
import com.ridvan.applicationing.util.ApplicationINGConstants.Companion.BASE_URL
import com.ridvan.applicationing.util.ApplicationINGConstants.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideAppContext(application: ApplicationING): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, AppDB::class.java, DB_NAME).build()
}
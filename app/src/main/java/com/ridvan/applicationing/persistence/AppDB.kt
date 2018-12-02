package com.ridvan.applicationing.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ridvan.applicationing.persistence.dao.ProjectDao
import com.ridvan.applicationing.persistence.entity.ProjectEntity

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */

@Database(entities = [(ProjectEntity::class)], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}
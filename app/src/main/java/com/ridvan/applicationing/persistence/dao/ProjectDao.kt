package com.ridvan.applicationing.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ridvan.applicationing.persistence.entity.ProjectEntity

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
@Dao
interface ProjectDao {
    @Query("SELECT * FROM projectentity ORDER BY id DESC")
    fun getProjects(): List<ProjectEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<ProjectEntity>)
}
package com.ridvan.applicationing.persistence

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.ridvan.applicationing.persistence.dao.ProjectDao
import com.ridvan.applicationing.persistence.entity.ProjectEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by ridvanozguvenir on 3.12.2018.
 */

@RunWith(AndroidJUnit4::class)
class ProjectDaoTest {

    lateinit var projectDao: ProjectDao
    lateinit var database: AppDB

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDB::class.java).build()
        projectDao = database.projectDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertAndGet() {
        val projects = listOf(ProjectEntity(1, "Name", "FullName", "owner"), ProjectEntity())
        projectDao.insertAll(projects)

        val allProjects = projectDao.getProjects()
        Assert.assertEquals(projects, allProjects)
    }

    @Test
    fun testUsersOrderedByCorrectly() {
        val projects = listOf(
            ProjectEntity(1, "Name", "FullName1", "owner1"),
            ProjectEntity(2, "Name2", "FullName2", "owner2"),
            ProjectEntity(3, "Name3", "FullName3", "owner3")
        )
        projectDao.insertAll(projects)

        val allProjects = projectDao.getProjects()
        val expectedProjects = projects.sortedByDescending { it.id }
        Assert.assertEquals(expectedProjects, allProjects)
    }

    @Test
    fun testConflictingInserts() {
        val projects = listOf(
            ProjectEntity(1, "Name", "FullName1", "owner1"),
            ProjectEntity(2, "Name2", "FullName2", "owner2"),
            ProjectEntity(3, "Name3", "FullName3", "owner3")
        )

        val projects2 = listOf(
            ProjectEntity(1, "Name", "FullName1", "owner1"),
            ProjectEntity(2, "Name2", "FullName2", "owner2"),
            ProjectEntity(4, "Name4", "FullName4", "owner4")
        )
        projectDao.insertAll(projects)
        projectDao.insertAll(projects2)

        val allProjects = projectDao.getProjects()
        val expectedProjects = listOf(
            ProjectEntity(4, "Name4", "FullName4", "owner4"),
            ProjectEntity(1, "Name", "FullName1", "owner1"),
            ProjectEntity(2, "Name2", "FullName2", "owner2"),
            ProjectEntity(3, "Name3", "FullName3", "owner3")
        )

        Assert.assertEquals(expectedProjects, allProjects)
    }
}
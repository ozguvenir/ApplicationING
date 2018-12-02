package com.ridvan.applicationing.repository

import com.ridvan.applicationing.persistence.dao.ProjectDao
import com.ridvan.applicationing.persistence.entity.ProjectList
import com.ridvan.applicationing.service.ApplicationINGService
import com.ridvan.applicationing.util.ConnectionHelper
import com.ridvan.applicationing.util.PreferencesHelper
import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.util.*

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */

private const val LAST_UPDATE_KEY = "last_update_page_"

class ProjectRepositoryImpl(
    private val appService: ApplicationINGService,
    private val projectDao: ProjectDao,
    private val connectionHelper: ConnectionHelper,
    private val preferencesHelper: PreferencesHelper
) : ProjectRepository {

    override fun getProjects(page: Int, forced: Boolean): Single<ProjectList> {
        return Single.create<ProjectList> { emitter: SingleEmitter<ProjectList> ->
            if (true) {
                loadProjectsFromNetwork(page, emitter)
            } else {
                loadOfflineProjects(page, emitter)
            }
        }
    }

    private fun shouldUpdate(page: Int, forced: Boolean) = when {
        forced -> true
        !connectionHelper.isOnline() -> false
        else -> {
            val lastUpdate = preferencesHelper.loadLong(LAST_UPDATE_KEY + page)
            val currentTime = Calendar.getInstance().timeInMillis
            lastUpdate + 1000 * 60 * 60 * 12 < currentTime
        }
    }

    private fun loadProjectsFromNetwork(page: Int, emitter: SingleEmitter<ProjectList>) {
        try {
            val projects = appService.getProjectDetails("ozguvenir").execute().body()
            if (projects != null) {
                //venueDao.insertAll(projects.items)
                val currentTime = Calendar.getInstance().timeInMillis
                preferencesHelper.saveLong(LAST_UPDATE_KEY + page, currentTime)
                //emitter.onSuccess(projects)
            } else {
                emitter.onError(Exception("No data received"))
            }
        } catch (exception: Exception) {
            emitter.onError(exception)
        }
    }

    private fun loadOfflineProjects(page: Int, emitter: SingleEmitter<ProjectList>) {
        val projects = projectDao.getProjects()
        if (!projects.isEmpty()) {
            emitter.onSuccess(ProjectList(projects))
        } else {
            emitter.onError(Exception("Device is offline"))
        }
    }
}
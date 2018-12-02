package com.ridvan.applicationing.repository

import com.ridvan.applicationing.persistence.dao.ProjectDao
import com.ridvan.applicationing.persistence.entity.ProjectList
import com.ridvan.applicationing.service.ApplicationINGService
import io.reactivex.Single
import io.reactivex.SingleEmitter

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */

class ProjectRepositoryImpl(
    private val appService: ApplicationINGService,
    private val projectDao: ProjectDao
) : ProjectRepository {

    override fun getProjects(userName: String): Single<ProjectList> {
        return Single.create<ProjectList> { emitter: SingleEmitter<ProjectList> ->
            if (true) { // TODO
                loadProjectsFromNetwork(userName, emitter)
            } else {
                loadOfflineProjects(emitter)
            }
        }
    }

    private fun loadProjectsFromNetwork(userName: String, emitter: SingleEmitter<ProjectList>) {
        try {
            val projects = appService.getProjectDetails(userName).execute().body()
            if (projects != null) {
                projectDao.insertAll(projects.items)
                emitter.onSuccess(projects)
            } else {
                emitter.onError(Exception("No data"))
            }
        } catch (exception: Exception) {
            emitter.onError(exception)
        }
    }

    private fun loadOfflineProjects(emitter: SingleEmitter<ProjectList>) {
        val projects = projectDao.getProjects()
        if (!projects.isEmpty()) {
            emitter.onSuccess(ProjectList(projects))
        } else {
            emitter.onError(Exception("Offline"))
        }
    }
}
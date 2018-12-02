package com.ridvan.applicationing.mocks.model

import com.ridvan.applicationing.persistence.entity.ProjectEntity
import com.ridvan.applicationing.persistence.entity.ProjectList
import com.ridvan.applicationing.repository.ProjectRepository
import io.reactivex.Single
import io.reactivex.SingleEmitter

/**
 * Created by ridvanozguvenir on 3.12.2018.
 */
class TestProjectRepository : ProjectRepository {
    override fun getProjects(): Single<ProjectList> {
        val projects = (1..10L).map {
            val number = it
            ProjectEntity(it, "Project $number", "", "")
        }

        return Single.create<ProjectList> { emitter: SingleEmitter<ProjectList> ->
            val projectList = ProjectList(projects)
            emitter.onSuccess(projectList)
        }
    }
}
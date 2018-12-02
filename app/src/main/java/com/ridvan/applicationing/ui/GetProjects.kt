package com.ridvan.applicationing.ui

import com.ridvan.applicationing.persistence.entity.ProjectList
import com.ridvan.applicationing.repository.ProjectRepository
import com.ridvan.applicationing.viewmodel.ProjectData
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
class GetProjects @Inject constructor(private val projectRepository: ProjectRepository) {

    fun execute(page: Int, forced: Boolean): Single<List<ProjectData>> {
        val projectList = projectRepository.getProjects(page, forced)
        return projectList.map { venueListModel: ProjectList? ->
            val items = venueListModel?.items ?: emptyList()
            items.map {
                ProjectData(
                    it.id,
                    it.name,
                    it.fullName,
                    it.owner
                )
            }
        }
    }
}
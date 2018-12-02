package com.ridvan.applicationing.repository

import com.ridvan.applicationing.persistence.entity.ProjectList
import io.reactivex.Single

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */

interface ProjectRepository {
    fun getProjects(userName: String): Single<ProjectList>
}
package com.ridvan.applicationing.mocks.di.module

import com.ridvan.applicationing.mocks.model.TestProjectRepository
import com.ridvan.applicationing.repository.ProjectRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ridvanozguvenir on 3.12.2018.
 */
@Module
class TestApplicationModule(
    private val projectRepository: ProjectRepository = TestProjectRepository()
) {

    @Provides
    @Singleton
    fun provideProjectRepository(): ProjectRepository {
        return projectRepository
    }
}
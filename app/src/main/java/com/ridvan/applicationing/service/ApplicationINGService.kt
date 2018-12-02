package com.ridvan.applicationing.service

import com.ridvan.applicationing.persistence.entity.ProjectList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
interface ApplicationINGService {
    @GET("users/{user}/repos")
    fun getProjectDetails(@Path("user") user: String): Call<ProjectList>
}
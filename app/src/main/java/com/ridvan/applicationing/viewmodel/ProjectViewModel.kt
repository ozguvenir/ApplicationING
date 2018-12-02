package com.ridvan.applicationing.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ridvan.applicationing.ui.GetProjects
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */

class ProjectViewModel @Inject constructor(
    private val getProjects: GetProjects
) : ViewModel() {

    val projectList: MutableLiveData<List<ProjectData>> = MutableLiveData()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val projects = mutableListOf<ProjectData>()

    init {
        projectList.value = projects
    }

    fun init() {
        getProjects()
    }

    fun getProjects() {
        compositeDisposable.add(
            getProjects.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ projects ->
                    this.projects.addAll(this.projects)
                    projectList.value = this.projects
                },
                    {
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
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
    val showLoading: MutableLiveData<Boolean> = MutableLiveData()
    val showError: MutableLiveData<Boolean> = MutableLiveData()

    private var loading = false
        set(value) {
            field = value
            if (value) {
                if (page == 1) {
                    showLoading.value = true
                }
            } else {
                showLoading.value = false
            }
        }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val projects = mutableListOf<ProjectData>()

    private var page = 1
    private var initialized = false

    init {
        projectList.value = projects
        showError.value = false
    }

    fun init() {
        if (!initialized) {
            getProjects()
            initialized = true
        }
    }

    fun getProjects(forced: Boolean = false) {
        loading = true
        val pageToRequest = if (forced) 1 else page
        compositeDisposable.add(
            getProjects.execute(pageToRequest, forced)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ projects ->
                    showError.value = false
                    if (forced) {
                        resetPaging()
                    }
                    if (page == 1) {
                        this.projects.clear()
                    }
                    this.projects.addAll(this.projects)
                    projectList.value = this.projects
                    loading = false
                    page++
                },
                    {
                        showError.value = true
                        loading = false
                        if (page == 1) {
                            initialized = false
                        }
                    })
        )
    }

    private fun resetPaging() {
        page = 1
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
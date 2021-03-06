package com.ridvan.applicationing.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridvan.applicationing.databinding.FragmentProjectListBinding
import com.ridvan.applicationing.util.ProjectListAdapter
import com.ridvan.applicationing.viewmodel.ProjectData
import com.ridvan.applicationing.viewmodel.ProjectViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_project_list.*
import javax.inject.Inject

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
class ProjectListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var projectViewModel: ProjectViewModel
    lateinit var userName: String

    private val adapter by lazy {
        val projectList = mutableListOf<ProjectData>()
        ProjectListAdapter(projectList) { project, view ->
            // TODO: click
        }
    }

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        projectViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProjectViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProjectListBinding.inflate(inflater, container, false)

        binding.viewModel = projectViewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter

        getButton.setOnClickListener {
            userName = editText.text.toString()
            projectViewModel.getProjects(userName)
        }
    }
}
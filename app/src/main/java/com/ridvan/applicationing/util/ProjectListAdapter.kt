package com.ridvan.applicationing.util

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridvan.applicationing.R
import com.ridvan.applicationing.viewmodel.ProjectData
import kotlinx.android.synthetic.main.list_item_project.view.*

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */

class ProjectListAdapter(
    private val projects: MutableList<ProjectData>,
    private val listener: (ProjectData, View) -> Unit
) : RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder>(), BindableAdapter<List<ProjectData>> {

    override fun setData(items: List<ProjectData>) {
        projects.clear()
        projects.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount() = projects.size

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) =
        holder.bind(projects[position], listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProjectViewHolder(parent.inflate(R.layout.list_item_project))

    fun addProjects(newProjects: List<ProjectData>) {
        projects.addAll(newProjects)
        notifyDataSetChanged()
    }

    fun clearProjects() {
        projects.clear()
    }

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("NewApi")
        fun bind(project: ProjectData, listener: (ProjectData, View) -> Unit) = with(itemView) {
            textViewTitle.text = project.name
            textViewDescription.text = project.fullName
        }
    }

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

}
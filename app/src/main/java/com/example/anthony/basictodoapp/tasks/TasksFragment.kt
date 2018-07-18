package com.example.anthony.basictodoapp.tasks

import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class TasksFragment : Fragment(), TasksContract.View {
    override lateinit var presenter: TasksContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded

    private lateinit var noTasksView: View
    private lateinit var noTaskIcon: ImageView
    private lateinit var noTaskMainView: TextView
    private lateinit var noTaskAddView: TextView
    private lateinit var tasksView: LinearLayout
    private lateinit var filteringLabelView: TextView


}
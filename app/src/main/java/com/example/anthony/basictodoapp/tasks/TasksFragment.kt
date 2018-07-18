package com.example.anthony.basictodoapp.tasks

import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.anthony.basictodoapp.R
import com.example.anthony.basictodoapp.data.Task

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

    // Listener for clicks on tasks in the view
    internal var itemListener: TaskItemListener = object : TaskItemListener {
        override fun onTaskClick(clickedTask: Task) {
            presenter.openTaskDetails(clickedTask)
        }

        override fun onCompleteTaskClick(completedTask: Task) {
            presenter.completeTask(completedTask)
        }

        override fun onActivateTaskClick(activatedTask: Task) {
            presenter.activateTask(activatedTask)
        }
    }

    private val listAdapter = TaskAdapter(ArrayList(0), itemListener)

    private class TaskAdapter(tasks: List<Task>, private val itemListener: TaskItemListener)
        : BaseAdapter() {

        var tasks: List<Task> = tasks
            set(tasks) {
                field = tasks
                notifyDataSetChanged()
            }

        override fun getCount() = tasks.size

        override fun getItem(i: Int) = tasks[i]

        override fun getItemId(i: Int) = i.toLong()

        override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
            val task = getItem(i)
            val rowView = view ?: LayoutInflater.from(viewGroup.context).inflate(R.layout.task_item, viewGroup, false)

            with(rowView.findViewById<TextView>(R.id.title))
        }
    }

    interface TaskItemListener {
        fun onTaskClick(clickedTask: Task)

        fun onCompleteTaskClick(completedTask: Task)

        fun onActivateTaskClick(activatedTask: Task)
    }
}
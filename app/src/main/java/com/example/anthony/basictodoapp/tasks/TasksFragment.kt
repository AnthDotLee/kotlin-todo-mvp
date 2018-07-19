package com.example.anthony.basictodoapp.tasks

import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.anthony.basictodoapp.R
import com.example.anthony.basictodoapp.data.Task
import org.w3c.dom.Text

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
    internal var itemListener: TaskAdapter.TaskItemListener = object : TaskItemListener {
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

    override fun setLoadingIndicator(active: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddTask() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskDetailsUi(taskId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskMarkedComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTaskMarkedActive() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCompletedTasksCleared() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingTasksError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showActiveFilterLabel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCompletedFilterLabel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAllFilterLabel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoActiveTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoCompletedTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSuccessfullySavedMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFilteringPopUpMenu() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private class TaskAdapter(tasks: List<Task>, private val itemListener: TaskItemListener) :
        BaseAdapter() {

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
            val rowView = view ?: LayoutInflater.from(viewGroup.context).inflate(
                R.layout.task_item,
                viewGroup,
                false
            )

            // The below block is the same as doing this:
            // rowView.findViewById<TextView>(R.id.title).text = task.titleForList
            with(rowView.findViewById<TextView>(R.id.title)) {
                text = task.titleForList
            }

            with(rowView.findViewById<CheckBox>(R.id.complete)) {
                // active/completed task ui
                isChecked = task.isCompleted
                val rowViewBackground =
                    if (task.isCompleted) R.drawable.list_completed_touch_feedback
                    else R.drawable.touch_feedback
                rowView.setBackgroundResource(rowViewBackground)
                setOnClickListener {
                    if(!task.isCompleted) {
                        itemListener.onCompleteTaskClick(task)
                    } else {
                        itemListener.onActivateTaskClick(task)
                    }
                }
            }
            rowView.setOnClickListener { itemListener. onTaskClick(task)}
            return rowView
        }
    }

    interface TaskItemListener {
        fun onTaskClick(clickedTask: Task)

        fun onCompleteTaskClick(completedTask: Task)

        fun onActivateTaskClick(activatedTask: Task)
    }
}
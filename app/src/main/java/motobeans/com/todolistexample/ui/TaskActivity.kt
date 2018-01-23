package motobeans.com.todolistexample.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.LayoutManager
import kotlinx.android.synthetic.main.activity_task.toolbar
import motobeans.com.todolistexample.Injection
import motobeans.com.todolistexample.R
import motobeans.com.todolistexample.adapters.recycler.DeliveryItemRecycler
import motobeans.com.todolistexample.databinding.ActivityTaskBinding
import motobeans.com.todolistexample.persistence.model.Task
import java.util.Random

/**
 * Created by swati on 23/01/18.
 */
class TaskActivity : AppCompatActivity() {
  private lateinit var binding: ActivityTaskBinding
  private lateinit var viewModelFactory: ViewModelFactory
  private lateinit var taskViewModel: TaskViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_task)

    setSupportActionBar(toolbar)

    init()
  }

  var tasks: List<Task>? = null

  private fun init() {
    viewModelFactory = Injection.provideViewModelFactory(this)
    taskViewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel::class.java)

    populateTaskList()

    taskViewModel.taskList.observe(this, Observer { tasks ->
      this.tasks = tasks
      populateUI()
    })
    binding.fab.setOnClickListener { _ -> addTask() }

    asyncAddTask(viewModelFactory).execute()
  }

  private fun populateUI() {
    binding.tvLiveDataSize.text = "Tasks count: ${tasks?.size}"
  }

  private val recylerViewLayoutManager: LayoutManager = LinearLayoutManager(this)

  private fun populateTaskList() {
    val adapter = DeliveryItemRecycler(this, taskViewModel)
    binding.includeMainContent.rvTasks.layoutManager = recylerViewLayoutManager
    binding.includeMainContent.rvTasks.adapter = adapter
    binding.includeMainContent.rvTasks.setHasFixedSize(true)
    binding.includeMainContent.rvTasks.isNestedScrollingEnabled = false
  }


  private fun addTask() {

    AddUpdateTaskActivity.start(this)
    //viewModelFactory.getDBSource().insertTask(Task(taskDesc = "My Task with random value : ${Random().nextInt()}"))
    //AsyncTask.execute({ viewModelFactory.getDBSource().insertTask(Task(taskDesc = "My Task with random value : ${Random().nextInt()}"))})
  }

  class asyncAddTask(val viewModelFactory: ViewModelFactory) : AsyncTask<String, String, Unit>() {
    override fun doInBackground(vararg params: String?) {
      val taskCount = viewModelFactory.getDBSource().getTaskCount()
      if (taskCount <= 0) {
        viewModelFactory.getDBSource().insertTask(Task(taskTitle = "Sample Task",
            taskDesc = "I need to complete this by Today at : ${Random().nextInt()}"))
      }
      return
    }
  }
}

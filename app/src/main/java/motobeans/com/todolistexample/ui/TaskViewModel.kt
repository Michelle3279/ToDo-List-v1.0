package motobeans.com.todolistexample.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import motobeans.com.todolistexample.persistence.dao.TaskDao
import motobeans.com.todolistexample.persistence.model.Task

/**
 * Created by swati on 23/01/18.
 */
class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

  val isOnline: MutableLiveData<Boolean> = MutableLiveData()

  val taskList: LiveData<List<Task>?> by lazy { taskDao.getAllTasks() }

  fun task(id: String) = taskDao.getTaskbyId(id)

  fun save(task: Task?) {
    AsyncTask.execute({ if (task != null) taskDao.insertTask(task) })
  }

  fun deleteTaskById(id: String) {
    AsyncTask.execute({ taskDao.deleteTaskById(id) })
  }
}
package motobeans.com.todolistexample.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import motobeans.com.todolistexample.persistence.dao.TaskDao

/**
 * Created by swati on 23/01/18.
 * Factory for ViewModels
 */
class ViewModelFactory(private val dataSource: TaskDao) : ViewModelProvider.Factory {
    fun getDBSource() = dataSource

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

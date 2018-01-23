package motobeans.com.todolistexample

import android.content.Context
import motobeans.com.todolistexample.persistence.dao.TaskDao
import motobeans.com.todolistexample.persistence.db.TaskDatabase
import motobeans.com.todolistexample.ui.ViewModelFactory

/**
 * Created by swati on 23/01/18.
 * Enables injection of data sources.
 */
object Injection {
    fun provideUserDataSource(context: Context): TaskDao {
        val database = TaskDatabase.getInstance(context)
        return database.taskDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}

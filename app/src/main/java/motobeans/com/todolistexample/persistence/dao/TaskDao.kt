package motobeans.com.todolistexample.persistence.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import motobeans.com.todolistexample.persistence.model.Task

/**
 * Created by swati on 23/01/18.
 */
@Dao
interface TaskDao {
  /**
   * Get all task stored in DB.

   * @return all tasks from the table
   */
  @Query("SELECT * FROM Task")
  fun getAllTasks() : LiveData<List<Task>?>

  /**
   * Get all task stored in DB.

   * @return all tasks from the table
   */
  @Query("SELECT COUNT(id) FROM Task")
  fun getTaskCount() : Int


  /**
   * Get a task by id.

   * @return the task from the table with a specific id.
   */
  @Query("SELECT * FROM Task WHERE id = :id LIMIT 1")
  fun getTaskbyId(id: String) : LiveData<Task>


  /**
   * Insert a task in the database. If the task already exists, replace it.
   * @param task the task to be inserted.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertTask(task: Task)


  /**
   * Delete task from table with specific id.
   */
  @Query("DELETE FROM Task WHERE id = :id")
  fun deleteTaskById(id: String)

  /**
   * Delete task from table via object
   */
  @Delete
  fun deleteTask(task: Task)

  /**
   * Delete all tasks.
   */
  @Query("DELETE FROM Task")
  fun deleteAllTasks()
}
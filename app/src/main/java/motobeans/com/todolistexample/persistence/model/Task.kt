package motobeans.com.todolistexample.persistence.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.UUID

/**
 * Created by swati on 23/01/18.
 */
@Entity(tableName = "task")
data class Task(

    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    var taskTitle: String,
    var taskDesc: String,
    var isActive: Boolean = true
)
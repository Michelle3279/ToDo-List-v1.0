package motobeans.com.todolistexample.adapters.holders

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import motobeans.com.todolistexample.R
import motobeans.com.todolistexample.databinding.ItemTaskBinding
import motobeans.com.todolistexample.persistence.model.Task
import motobeans.com.todolistexample.ui.AddUpdateTaskActivity
import motobeans.com.todolistexample.ui.TaskViewModel

/**
 * Created by swati on 23/01/18.
 */
class DeliveryItemHolder(val context: Context,
    val binding: ItemTaskBinding, val taskViewModel: TaskViewModel) : RecyclerView.ViewHolder(binding.root) {

  fun handleCard(position: Int, itemData: Task) {
    binding.tvTaskTitle.text = itemData.taskTitle
    binding.tvTaskDesc.text = itemData.taskDesc

    if(itemData.isActive) {
      binding.tvStatus.text = "De-Activate"
      binding.tvStatus.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
    } else {
      binding.tvStatus.text = "Activate"
      binding.tvStatus.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
    }

    binding.tvStatus.setOnClickListener { _ ->
      itemData.isActive = !itemData.isActive
      taskViewModel.save(task = itemData)
    }
    binding.tvEdit.setOnClickListener { _ -> AddUpdateTaskActivity.start(context, itemData.id) }
    binding.tvDelete.setOnClickListener { _ -> deleteProduct(itemData.id)}
    binding.root.setOnClickListener { _ -> AddUpdateTaskActivity.start(context, itemData.id) }
  }

  private fun deleteProduct(itemId: String) {
    val builder = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
    builder.setTitle("Delete!")
    builder.setMessage("Do you really want to Delete this task ?")
    builder.setPositiveButton(android.R.string.yes, { dialog, _ ->
      taskViewModel.deleteTaskById(id = itemId)
      dialog.dismiss()
    })
    builder.setNegativeButton(android.R.string.cancel, { dialog, _ ->
      dialog.dismiss()
    })
    val dialog = builder.create()

    dialog.show()
  }
}
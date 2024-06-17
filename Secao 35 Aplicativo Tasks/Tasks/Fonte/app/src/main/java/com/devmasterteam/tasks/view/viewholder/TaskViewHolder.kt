package com.devmasterteam.tasks.view.viewholder

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.databinding.RowTaskListBinding
import com.devmasterteam.tasks.listeners.TaskListener
import com.devmasterteam.tasks.models.entities.TaskModel
import com.devmasterteam.tasks.viewmodel.PriorityViewModel
import java.text.SimpleDateFormat

class TaskViewHolder(private val itemBinding: RowTaskListBinding, private val listener: TaskListener) :
    RecyclerView.ViewHolder(itemBinding.root) {

    @SuppressLint("SimpleDateFormat")
    fun bindData(task: TaskModel) {

        itemBinding.textDescription.text = task.description
        val priority = PriorityViewModel.findById(task.priorityId)
        itemBinding.textPriority.text = priority?.description ?: ""
        val date = SimpleDateFormat("yyyy-MM-dd").parse(task.dueDate)
        itemBinding.textDueDate.text = date?.let { SimpleDateFormat("dd/MM/yyyy").format(it) }

        if (task.complete) {
            itemBinding.imageTask.setImageResource(R.drawable.ic_done)
        } else {
            itemBinding.imageTask.setImageResource(R.drawable.ic_todo)
        }

        itemBinding.textDescription.setOnClickListener { listener.onListClick(task.id) }
        itemBinding.imageTask.setOnClickListener {
            if (task.complete) {
                listener.onUndoClick(task.id)
            } else {
                listener.onCompleteClick(task.id)
            }
        }

        itemBinding.textDescription.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_de_tarefa)
                .setMessage(R.string.remover_tarefa)
                .setPositiveButton(R.string.sim) { dialog, which ->
                    listener.onDeleteClick(task.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()
            true
        }

    }
}